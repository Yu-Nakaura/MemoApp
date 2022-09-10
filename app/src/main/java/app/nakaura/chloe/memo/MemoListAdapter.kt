package app.nakaura.chloe.memo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.nakaura.chloe.memo.databinding.ItemDataCellBinding

class MemoListAdapter(val memoList: MutableList<String>) :
    RecyclerView.Adapter<MemoListAdapter.ListViewHolder>() {

    class ListViewHolder(val binding: ItemDataCellBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemDataCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.memoWord.text = memoList[position]
    }

    override fun getItemCount(): Int = memoList.size

    fun updateMemo(newList: List<String>) {
        memoList.clear()
        memoList.addAll(newList)
        notifyDataSetChanged()
    }
}

