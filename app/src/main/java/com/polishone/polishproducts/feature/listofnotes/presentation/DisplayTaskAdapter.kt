package com.polishone.polishproducts.feature.listofnotes.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.polishone.polishproducts.common.utils.priority.PriorityColor
import com.polishone.polishproducts.common.utils.priority.SetPriorityColor
import com.polishone.polishproducts.databinding.CreateNoteItemBinding
import com.polishone.polishproducts.feature.listofnotes.data.model.Note
import kotlinx.coroutines.NonDisposableHandle.parent

class DisplayTaskAdapter(private val notes: List<Note>) : RecyclerView.Adapter<DisplayTaskAdapter.DisplayTaskViewHolder>() {

    inner class DisplayTaskViewHolder(private val binding: CreateNoteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Note, context: Context) {
            // initialize the views
            binding.createNoteItemTitleTv.text = task.title
            binding.createNoteItemContentTv.text = task.content
            val drawable = binding.createNotePriorityIv.drawable
            val wrapDrawable = DrawableCompat.wrap(drawable)
            val priorityColor = when (task.taskPriority) {
                1 -> PriorityColor.LOW
                2 -> PriorityColor.MEDIUM
                else -> PriorityColor.HIGH
            }
            SetPriorityColor.setDrawableColor(context, drawable, priorityColor.getColor())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplayTaskViewHolder {
        val view = CreateNoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DisplayTaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: DisplayTaskViewHolder, position: Int) {
        val currentItem = notes[position]
        holder.bind(currentItem, holder.itemView.context)
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}
