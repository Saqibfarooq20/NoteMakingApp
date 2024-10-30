package com.example.mynotemakingapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotemakingapp.databinding.ItemViewBinding
import com.example.mynotemakingapp.model.Note
import kotlin.random.Random
//import java.*


class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHoder>() {

    inner class NoteViewHoder(val itemBinding : ItemViewBinding):
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback <Note>(){


        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.noteTitle == newItem.noteTitle &&
                    oldItem.noteBody == newItem.noteBody
        }


        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {

            return oldItem == newItem

        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    // 3 methods of recyclerview

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHoder {
        return NoteViewHoder(
            ItemViewBinding.inflate(
                LayoutInflater.from(parent.context), parent,false
            )
        )
    }



    override fun onBindViewHolder(holder: NoteViewHoder, position: Int) {
        val currentNote = differ.currentList[position]
        holder.itemBinding.textViewItemTitle.text = currentNote.noteTitle
        holder.itemBinding.textViewItemBody.text = currentNote.noteBody

        // color generate here
        val random = Random
        val color = Color.argb(
            255,
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256)
        )


        // setting the color here
        holder.itemBinding.View.setBackgroundColor(color)


        holder.itemBinding.cardItemView.setOnClickListener {

            val direction = HomeFragmentDirections.actionHomeFragmentToUpdateFragment(currentNote)
            it.findNavController().navigate(direction)

        }
    }
    override fun getItemCount(): Int {
        return differ.currentList.size
    }



}