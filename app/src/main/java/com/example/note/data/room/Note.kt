package com.example.note.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NoteTable")
data class Note(
    @PrimaryKey
    val id :String,
    val title :String
)