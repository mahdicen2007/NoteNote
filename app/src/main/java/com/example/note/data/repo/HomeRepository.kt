package com.example.note.data.repo

import com.example.note.data.room.Note


interface HomeRepository {

    fun getAllNotes() :List<Note>
    fun insertNote(newNote : Note) :String

}