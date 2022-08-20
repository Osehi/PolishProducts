package com.polishone.polishproducts.feature.listofnotes.presentation

import androidx.recyclerview.widget.RecyclerView
import com.polishone.polishproducts.databinding.CreateNoteItemBinding
import com.polishone.polishproducts.feature.listofnotes.data.model.Note

class DisplayTaskAdapter {

    class DisplayTaskViewHolder(private val binding: CreateNoteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Note) {
            // initialize the views
            binding.createNoteItemTitleTv.text = task.title
            binding.createNoteItemContentTv.text = task.content
        }
    }
}
