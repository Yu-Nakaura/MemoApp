package app.nakaura.chloe.memo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.nakaura.chloe.memo.databinding.ActivityMainBinding
import app.nakaura.chloe.memo.databinding.FragmentMemoListBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MemoListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MemoListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentMemoListBinding? = null
    private val binding get() = _binding!!
    val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
    private lateinit var memoList: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //arguments?.let {
            //param1 = it.getString(ARG_PARAM1)
            //param2 = it.getString(ARG_PARAM2)
        //}
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMemoListBinding.inflate(inflater, container, false)

        loadMemo()
        println(memoList)
        binding.myRecyclerView.layoutManager = layoutManager

        val memoListAdapter = MemoListAdapter(memoList)
        binding.myRecyclerView.adapter = memoListAdapter

        //memoListAdapter.updateMemo(memoList)
        println(memoList)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MemoListFragment.
         */
        // TODO: Rename and change types and number of parameters
        /*@JvmStatic
        fun newInstance(param1: String, param2: String) =
            MemoListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.plusButton.setOnClickListener {
            //Log.d("log", "FirstText was pressed!")
            val memoCreateFragment = MemoCreateFragment()
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.replace(R.id.fragmentContainer, memoCreateFragment)
            fragmentTransaction?.commit()
        }
    }

    private fun loadMemo() {
        memoList = mutableListOf<String>(
            "Test1",
            "Test2",
            "Test3",
            "Test4"
        )
    }
}