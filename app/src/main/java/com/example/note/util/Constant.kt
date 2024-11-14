package com.example.note.util

import androidx.compose.material.darkColors
import com.example.note.R
import com.example.note.data.room.Note


const val BaseUrl = "https://team-git.iran.liara.run/"
val prepNote = Note(
    title = "یادداشت کن یادت نره!",
    detail = "هر موقع خواستی هر چی دلت خواست یادداشت کنیا" +
            "\n" + "فردا نگی یادم رفته!",
    background = R.color.white,
    isImaged = true,
    image = R.drawable.note
)