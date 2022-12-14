package app.nakaura.chloe.memo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.nakaura.chloe.memo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val memoListFragment = MemoListFragment()
        // 1. call the start of edition
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        // 2. Link the Fragment manager class and place with FragmentManager
        fragmentTransaction.add(R.id.fragmentContainer, memoListFragment)
        // 3.Seal the modification
        fragmentTransaction.commit()
    }
}
