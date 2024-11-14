package com.example.note.data.repo

import com.example.note.data.room.Note
import com.example.note.data.room.NoteDao

class HomeRepoImpl(
    private val noteDao: NoteDao
) :HomeRepository {
    override fun getAllNotes(): List<Note> {
        return noteDao.getAllNotes()
    }

    override fun insertNote(newNote: Note) :String {
        noteDao.insertNote(newNote)
        return "Inserted"
    }
}