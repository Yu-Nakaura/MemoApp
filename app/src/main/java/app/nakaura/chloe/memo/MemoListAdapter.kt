package app.nakaura.chloe.memo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.nakaura.chloe.memo.databinding.ItemDataCellBinding

data class Memo(val name: String)

// 独自のAdapter
class MemoListAdapter : RecyclerView.Adapter<ViewHolderList>() {
    private val memoList: MutableList<Memo> = mutableListOf()

    // ViewHolderの作成
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderList {
        val binding = ItemDataCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderList(binding)
    }

    // ViewHolderの設定
    override fun onBindViewHolder(holder: ViewHolderList, position: Int) {

        val memo = memoList[position]
        holder.binding.itemTextView.text = memo.name
        //holder.memoList.text = memoList[position]
    }

    // ViewHolderの数の決定
    override fun getItemCount(): Int = memoList.size

    fun updateMemo(newList: List<Memo>) {
        memoList.clear()
        memoList.addAll(newList)
        notifyDataSetChanged()
    }
}

