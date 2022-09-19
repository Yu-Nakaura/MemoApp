package app.nakaura.chloe.memo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.nakaura.chloe.memo.databinding.ItemDataCellBinding

//reviewed by toppo 🧸: RecyclerViewが使えていてGood！！
class MemoListAdapter(private val memoList: ArrayList<String>) :
    RecyclerView.Adapter<MemoListAdapter.ListViewHolder>() {

    class ListViewHolder(val binding: ItemDataCellBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemDataCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.memoWord.text = memoList[position]
        //🧸:必要ないコードは消しちゃおう〜
        /*holder.binding.memoWord.setOnClickListener{
            memoList.removeAt(position)
            Log.d("listPosition", memoList[position])
            holder.binding.memoWord.text = memoList[position]
        }*/
    }

    override fun getItemCount(): Int = memoList.size

}


