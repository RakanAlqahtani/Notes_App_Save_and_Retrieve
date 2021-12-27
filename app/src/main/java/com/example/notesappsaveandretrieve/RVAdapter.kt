package com.example.notesappsaveandretrieve

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.items_row.view.*

class RVAdapter(var notes : ArrayList<Note>):RecyclerView.Adapter<RVAdapter.ItemsViewHolder>(){
    class ItemsViewHolder(itemsView : View): RecyclerView.ViewHolder(itemsView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.items_row,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val text = notes[position]

        holder.itemView.apply {
            tvNote.text = "${text.note}"
        }
    }

    override fun getItemCount() = notes.size

    fun update(notes : ArrayList<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }
}