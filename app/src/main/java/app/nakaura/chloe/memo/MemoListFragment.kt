package app.nakaura.chloe.memo

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.nakaura.chloe.memo.databinding.FragmentMemoListBinding


class MemoListFragment : Fragment() {
    private var _binding: FragmentMemoListBinding? = null
    private val binding get() = _binding!!
    private val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
    private lateinit var memoList: MutableList<String>
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMemoListBinding.inflate(inflater, container, false)

        memoList = mutableListOf<String>(
            "Test1",
            "Test2",
            "Test3",
            "Test4"
        )

        sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)
        //リストを引き出す
        val previousList: MutableList<String>? =
            sharedPref.getStringSet("currentList", setOf<String>())?.toMutableList()
        Log.d("previousArray", previousList.toString())

        if (previousList != null) {
            memoList = previousList
        }
        updateMemo()

        binding.myRecyclerView.layoutManager = layoutManager
        val memoListAdapter = MemoListAdapter(memoList as ArrayList<String>)
        binding.myRecyclerView.adapter = memoListAdapter
        return binding.root
    }

    companion object {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.plusButton.setOnClickListener {
            //Log.d("log", "Plus button was pressed!")
            Log.d("wordArray2", memoList.toString())
            val memoCreateFragment = MemoCreateFragment()
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.replace(R.id.fragmentContainer, memoCreateFragment)
            fragmentTransaction?.commit()
        }
    }

    private fun updateMemo() {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)

        //新しいメモを受け取る
        val addedWord: String? = sharedPref.getString("newWord", "")
        //リストに追加する
        if (addedWord != null) {
            memoList.add(addedWord)
        }
        //リストを保存する
        val editor = sharedPref.edit()
        editor.putStringSet("currentList", memoList.toSet())
        editor.apply()
    }
}