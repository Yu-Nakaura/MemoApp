package app.nakaura.chloe.memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.nakaura.chloe.memo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

    }
}