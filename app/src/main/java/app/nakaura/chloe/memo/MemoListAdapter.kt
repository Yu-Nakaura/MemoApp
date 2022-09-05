package app.nakaura.chloe.memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.nakaura.chloe.memo.databinding.ActivityAdapterBinding
import app.nakaura.chloe.memo.databinding.ActivityMainBinding
import app.nakaura.chloe.memo.databinding.ItemDataCellBinding

data class Memo(val name: String)

// 独自のAdapter
class MemoListAdapter : RecyclerView.Adapter<MemoListViewHolder>() {
    private val memoList: MutableList<String> = mutableListOf()

    // ViewHolderの作成
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupListViewHolder {
        val binding = ItemDataCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemoListViewHolder(binding)

    }

    // ViewHolderの設定
    override fun onBindViewHolder(holder: MemoListViewHolder, position: Int) {

        val memo = memoList[position]
        holder.binding.nameTextView.text = memo.name
    }

    // ViewHolderの数の決定
    override fun getItemCount(): Int = memoList.size

    fun updateMemo(newList: List<Memo>) {
        memoList.clear()
        memoList.addAll(newList)
        notifyDataSetChanged()
    }
}

