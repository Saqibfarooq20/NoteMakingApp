package com.example.mynotemakingapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mynotemakingapp.databinding.ActivityMainBinding
import com.example.mynotemakingapp.model.NoteDataBase
import com.example.mynotemakingapp.model.NoteRepository
import com.example.mynotemakingapp.view_model.NoteViewModel
import com.example.mynotemakingapp.view_model.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding


    lateinit var noteViewModel : NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


       setUpViewModel()
    }



    private fun setUpViewModel() {
        val noteRepository = NoteRepository(NoteDataBase(this))
        val viewModelProviderFactory = NoteViewModelFactory(application,noteRepository)

        noteViewModel= ViewModelProvider(this,viewModelProviderFactory).get(NoteViewModel::class.java)
    }
}