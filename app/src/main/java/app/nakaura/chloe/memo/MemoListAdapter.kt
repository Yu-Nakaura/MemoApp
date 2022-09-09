package app.nakaura.chloe.memo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.nakaura.chloe.memo.databinding.ItemDataCellBinding

// 独自のAdapter
class MemoListAdapter : RecyclerView.Adapter<ListViewHolder>() {
    private val memoListData: MutableList<Memo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemDataCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    // ViewHolderの設定
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val memo = memoListData[position]
        holder.binding.itemTextView.text = memo.name

    }

    // ViewHolderの数の決定
    override fun getItemCount(): Int = memoListData.size

    fun updateMemo(newList: List<Memo>) {
        memoListData.clear()
        memoListData.addAll(newList)
        notifyDataSetChanged()
    }
}

