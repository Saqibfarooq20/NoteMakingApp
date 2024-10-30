package com.example.mynotemakingapp.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotemakingapp.model.Note
import com.example.mynotemakingapp.model.NoteRepository
import kotlinx.coroutines.launch


class NoteViewModel(app: Application, private val repository: NoteRepository) :
    AndroidViewModel(app) {
    fun addNote(note: Note) = viewModelScope.launch {
        repository.insertNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }

    fun getAllNote() = repository.getAllNote()

    fun searchNote(query: String?) = repository.searchNote(query)
}