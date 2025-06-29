package com.example.notetakingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notetakingapp.adapter.NoteAdapter
import com.example.notetakingapp.databinding.FragmentHomeBinding
import com.example.notetakingapp.model.Note
import com.example.notetakingapp.view_model.NoteViewModel


class HomeFragment : Fragment(R.layout.fragment_home) ,SearchView.OnCloseListener {

    private var _binding  : FragmentHomeBinding = null
    private val binding get() = _binding !!

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel=(activity as MainActivity).noteViewModel

        setUpRecyclerView()
        binding.floatingHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_newNoteFragment)
        }

    }
    private fun setUpRecyclerView(){

        noteAdapter = NoteAdapter()

        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = noteAdapter
        }

        activity?.let {
            noteViewModel.getAllNote().observe(
                viewLifecycleOwner,{
                    note -> noteAdapter.differ.submitList(note)
                    updateUI(note)
                }
            )
        }

    }

    private fun updateUI(note: List<Note>?) {
       if(note.isNotEmpty()){
           binding.cardView.visibility = View.GONE
           binding.recyclerView.visibility = View.VISIBLE
       }
    }else{
        binding.cardView.visibility=View.VISIBLE
        binding.recyclerView.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        menu.clear()
        inflater.inflate(R.menu.home_menu,menu)

        val mMenuSearch = menu.findItem(R.menu.home_menu.action_homemenu as SearchView)
    }


}