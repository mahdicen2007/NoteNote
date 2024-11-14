package com.example.note.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NoteTable")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id :Int? = null,
    val title :String,
    val detail :String,
    val background :Int,
    val isImaged :Boolean,
    val image :Int,
    val date :String
)