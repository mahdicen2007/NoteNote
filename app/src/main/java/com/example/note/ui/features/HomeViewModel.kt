package com.example.note.ui.features

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.data.repo.HomeRepository
import com.example.note.data.room.Note
import com.example.note.util.prepNote
import com.example.note.util.prepNote1
import com.example.note.util.prepNote2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository
) : ViewModel() {

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    private val _isLoading = MutableStateFlow<Boolean>(false)

    val notes: StateFlow<List<Note>> = _notes
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        insertEx(prepNote)
        insertEx(prepNote1)
        insertEx(prepNote2)
        getAllNotes()
    }

    fun insertEx(newNote: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.insertNote(prepNote)
            } catch (ex:Exception) {
                Log.v("testEx1" , ex.message.toString())
            }
        }
    }

    fun getAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _notes.value = repository.getAllNotes()
            } catch (ex :Exception) {
                Log.v("testEx2" , ex.message.toString())
            }
        }
    }

}