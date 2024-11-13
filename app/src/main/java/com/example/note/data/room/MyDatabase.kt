package com.example.note.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1 , entities = [Note::class] , exportSchema = false)
abstract class MyDatabase() :RoomDatabase() {
    abstract fun noteDao() :NoteDao
}