package app.nakaura.chloe.memo


import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.nakaura.chloe.memo.databinding.FragmentMemoCreateBinding

class MemoCreateFragment : Fragment() {
    private var _binding: FragmentMemoCreateBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMemoCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.checkButton.setOnClickListener {
            //Log.d("log", "Check button was pressed!")
            if (binding.editText.text != null) {
                val typedWord: String = binding.editText.text.toString()
                Log.d("typedWord", typedWord)

                sharedPref = getDefaultSharedPreferences(activity)
                //新しいメモをわたす
                val editor = sharedPref.edit()
                editor.putString("newWord", typedWord)
                editor.apply()
            }

            val memoListFragment = MemoListFragment()
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.replace(R.id.fragmentContainer, memoListFragment)
            fragmentTransaction?.commit()
        }
    }

}