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
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import com.example.mynotemakingapp.databinding.FragmentHomeBinding
import com.example.mynotemakingapp.databinding.FragmentNewBinding
import com.example.mynotemakingapp.model.Note
import com.example.mynotemakingapp.view_model.NoteViewModel


@Suppress("DEPRECATION")
class NewFragment : Fragment(R.layout.fragment_new) {
    private var _binding: FragmentNewBinding? = null
    private val binding get() = _binding!!
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var mView : View



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = (activity as MainActivity).noteViewModel
        mView = view
    }

    private fun saveNote(view: View){
        val noteTitle = binding.editTextTitle.text.toString()
        val noteBody = binding.editTextBody.text.toString()

        if(noteTitle.isNotEmpty()){
            val note = Note(0,noteTitle,noteBody)
            noteViewModel.addNote(note)
            Toast.makeText(mView.context, "Note Save SuccessFully", Toast.LENGTH_SHORT).show()

            view.findNavController().navigate(R.id.action_newFragment_to_homeFragment)
        }
        else{
            Toast.makeText(mView.context, "Please Enter Note Title", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        menu.clear()
        inflater.inflate(R.menu.new_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.save ->{
                saveNote(mView)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}