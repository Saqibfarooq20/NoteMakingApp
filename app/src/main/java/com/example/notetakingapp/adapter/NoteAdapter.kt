package com.example.notetakingapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notetakingapp.HomeFragmentDirections
import com.example.notetakingapp.databinding.ItemLayoutBinding
import com.example.notetakingapp.model.Note
//import kotlin.random.Random
//import java.*


class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHoder>() {

    inner class NoteViewHoder(val itemBinding : ItemLayoutBinding):
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback <Note>(){


        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.title == newItem.title &&
                    oldItem.body == newItem.body
        }


        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {

            return oldItem == newItem

        }
    }

    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHoder {
      return NoteViewHoder(
          ItemLayoutBinding.inflate(
              LayoutInflater.from(parent.context), parent,false
          )
      )
    }



    override fun onBindViewHolder(holder: NoteViewHoder, position: Int) {
        val currentNote = differ.currentList[position]
        holder.itemBinding.itemTitle.text = currentNote.title
        holder.itemBinding.itemBody.text = currentNote.body

//        val random = Random()
//        val color = color.argb(
//            255,
//            random.nextInt(256),
//            random.nextInt(256),
//            random.nextInt(256)
//        )

        holder.itemBinding.itemLayout.setOnClickListener {

            val direction = HomeFragmentDirections.
            actionHomeFragmentToUdateNoteFragment(currentNote)

            it.findNavController().navigate(direction)

        }
    }
    override fun getItemCount(): Int {
        return differ.currentList.size
    }



}