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
import org.json.JSONArray
import org.json.JSONException

//reviewed by toppo 🧸: レイアウトや画面遷移は良さそう！けどアプリが落ちちゃう状態っぽいので、あと1歩修正が必要><
class MemoListFragment : Fragment() {
    private var _binding: FragmentMemoListBinding? = null
    private val binding get() = _binding!!
    private val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
    private lateinit var sharedPref: SharedPreferences
    private val currentArray = JSONArray()
    private var memoList: ArrayList<String> = arrayListOf(
        "Test1",
        "Test2",
        "Test3",
        "Test4"
    )

    //🧸: 要らないライフサイクルは削除しよう！
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMemoListBinding.inflate(inflater, container, false)

        loadMemo()
        //🧸: 必要ないコードは消しちゃおう！
        /*for (i in 0 until memoList.size) {
            currentArray.put(memoList[i])
        }*/
        //Log.d("FirstCurrentArray", currentArray.toString())
        //Log.d("finalMemoList", memoList.toString())


        //🧸: エラーログを見るとここが原因でアプリが落ちてしまっていそう！addMemoのメソッドを再確認してみよう
        addMemo()
        //Log.d("updatedCurrentArray", currentArray.toString())
        //Log.d("finalMemoList", memoList.toString())

        saveMemo()
        //Log.d("finalMemoList", memoList.toString())

        binding.myRecyclerView.layoutManager = layoutManager
        val memoListAdapter = MemoListAdapter(memoList)
        binding.myRecyclerView.adapter = memoListAdapter
        return binding.root
    }

    companion object;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.plusButton.setOnClickListener {
            val memoCreateFragment = MemoCreateFragment()
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.replace(R.id.fragmentContainer, memoCreateFragment)
            fragmentTransaction?.commit()

        }
    }

    //🧸: メソッド化が出来ていてNice！
    private fun loadMemo() {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)
        //リストをStringで引き出す
        val previousListString: String? = sharedPref.getString("currentList", "")
        if (previousListString != null) {
            //Log.d("previousListString", previousListString.toString())
            val previousList = ArrayList<String>()
            try {
                val currentArray = JSONArray(previousListString)
                //ワードごとに分ける
                for (i in 0 until currentArray.length()) {
                    val word: String = currentArray.optString(i)
                    //Log.d("word", word)
                    //新しいリストにいれていく
                    previousList.add(word)
                    //Log.d("previousList", previousList.toString())
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            memoList.clear()
            memoList.addAll(previousList)
            //Log.d("memoList", memoList.toString())
            //Log.d("currentArray", currentArray.toString())
        }
        for (i in 0 until memoList.size) {
            currentArray.put(memoList[i])
            //Log.d("currentArray55", currentArray.toString())
        }
    }

    //🧸: 1番最初のSharedPreferencesが空の状態の時にアプリが落ちてしまっている…！
    private fun addMemo() {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)
        //新しいメモをMemoCreateFragmentから受け取る
        val addedWord: String? = sharedPref.getString("newWord", "")
        //🧸: 更新しない変数はvalで定義しよう
        var finalWord:String = memoList[memoList.size - 1]

        //リストに追加する
        if (addedWord != null && finalWord != addedWord) {
            if (addedWord.isNotEmpty()) {
                memoList.add(addedWord)
                currentArray.put(addedWord)
                //Log.d("currentArray(addMemo)", currentArray.toString())
            } else {
                currentArray.put(null)
            }
        }
    }

    private fun saveMemo() {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)
        //リストをStringで保存する
        val editor = sharedPref.edit()
        editor.putString("currentList", currentArray.toString())
        editor.apply()
        //Log.d("currentArray(saveMemo)", currentArray.toString())
    }
}


