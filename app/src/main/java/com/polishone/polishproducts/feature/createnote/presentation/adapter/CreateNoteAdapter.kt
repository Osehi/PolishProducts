package com.polishone.polishproducts.feature.createnote.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.polishone.polishproducts.databinding.CreateNoteItemBinding

class CreateNoteAdapter(private val notes: ArrayList<Nothing>) {

    class CreateNoteHolder(private val binding: CreateNoteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData() {
            // initialize the views
            binding.createNoteItemTitleTv.text
        }
    }
}
