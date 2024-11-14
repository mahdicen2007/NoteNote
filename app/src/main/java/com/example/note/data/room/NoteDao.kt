package com.example.note.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {

    @Query("SELECT * FROM NoteTable")
    fun getAllNotes() :List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(newNote :Note)

}