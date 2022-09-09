package app.nakaura.chloe.memo

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import app.nakaura.chloe.memo.databinding.ItemDataCellBinding

class ListViewHolder(val binding: ItemDataCellBinding) : RecyclerView.ViewHolder(binding.root) {
    var Memolist: TextView = itemView.findViewById(R.id.list_name)
}


