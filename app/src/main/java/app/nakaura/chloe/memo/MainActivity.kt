package app.nakaura.chloe.memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import app.nakaura.chloe.memo.databinding.ActivityMainBinding
import app.nakaura.chloe.memo.databinding.ItemDataCellBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val memoListFragment = MemoListFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainer, memoListFragment)
        fragmentTransaction.commit()
    }
}
