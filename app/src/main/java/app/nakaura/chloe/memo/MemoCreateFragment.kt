package app.nakaura.chloe.memo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import app.nakaura.chloe.memo.databinding.FragmentMemoCreateBinding
import app.nakaura.chloe.memo.databinding.FragmentMemoListBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MemoCreateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MemoCreateFragment : Fragment() {
    // TODO: Rename and change types of parameters
    //private var param1: String? = null
    //private var param2: String? = null
    private var _binding: FragmentMemoCreateBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMemoCreateBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MemoCreateFragment.
         */
        // TODO: Rename and change types and number of parameters
        /*@JvmStatic
        fun newInstance(param1: String, param2: String) =
            MemoCreateFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.checkButton.setOnClickListener {
            //Log.d("log", "Check button was pressed!")
            sharedPref = getDefaultSharedPreferences(activity)
            val wordArray: MutableList<String>? = sharedPref.getStringSet("currentList", setOf<String>())?.toMutableList()
            Log.d("wordArray", wordArray.toString())

            if(binding.editText.text != null){
                val typedWord:String = binding.editText.text.toString()
                Log.d("typedWord", typedWord)

                wordArray?.add(typedWord)

                val editor = sharedPref.edit()
                editor.putStringSet("newList", wordArray?.toSet())
                editor.apply()
                Log.d("newList", wordArray.toString())

            }



            val memoListFragment = MemoListFragment()
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.replace(R.id.fragmentContainer, memoListFragment)
            fragmentTransaction?.commit()
        }
    }

}