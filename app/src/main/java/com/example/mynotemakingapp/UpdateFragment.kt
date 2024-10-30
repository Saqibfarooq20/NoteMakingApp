package com.example.mynotemakingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mynotemakingapp.databinding.FragmentHomeBinding
import com.example.mynotemakingapp.databinding.FragmentUpdateBinding
import com.example.mynotemakingapp.model.Note
import com.example.mynotemakingapp.view_model.NoteViewModel


@Suppress("DEPRECATION")
class UpdateFragment : Fragment(R.layout.fragment_update) {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var currentNote : Note
    private val args : UpdateFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentUpdateBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = (activity as MainActivity).noteViewModel
        currentNote = args.note!!

        binding.updateTextTitle.setText(currentNote.noteTitle)
        binding.updateTextBody.setText(currentNote.noteBody)

        binding.floatingActionBtnUpdare.setOnClickListener {
            val title = binding.updateTextTitle.text.toString().trim()
            val body = binding.updateTextBody.text.toString().trim()

            if (title.isNotEmpty()){
                val note = Note(currentNote.id,title,body)
                noteViewModel.updateNote(note)
                view.findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
            }
            else{
                Toast.makeText(context, "Please Enter Title", Toast.LENGTH_SHORT).show()
            }
        }


    }
    //----------------------    error ---------------------------

    private fun deleteNote(){
       AlertDialog.Builder(requireContext()).apply {
           setTitle("Delete Note")
           setMessage("You Want To Delete This Note ? ")
           setPositiveButton("Delete"){_,_->
               noteViewModel.deleteNote(currentNote)

               view?.findNavController()?.navigate(
                   R.id.action_updateFragment_to_homeFragment
               )


           }

           setNegativeButton("Cancel",null)

       }.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        menu.clear()
        inflater.inflate(R.menu.update_memu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.delete ->{
                deleteNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}