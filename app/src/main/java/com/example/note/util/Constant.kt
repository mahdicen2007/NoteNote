package com.example.note.util

import android.annotation.SuppressLint
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.darkColors
import androidx.compose.ui.platform.LocalContext
import com.example.note.R
import com.example.note.data.room.Note
import java.time.LocalDateTime


const val BaseUrl = "https://team-git.iran.liara.run/"
@SuppressLint("NewApi")
val prepNote = Note(
    title = "یادداشت کن یادت نره!",
    detail = "هر موقع خواستی هر چی دلت خواست یادداشت کنیا" +
            "\n" + "فردا نگی یادم رفته!",
    background = R.color.white,
    isImaged = true,
    image = R.drawable.note,
    date = "همین الان"
)
val prepNote1 = Note(
    title = "یادداشت کن یادت نره!",
    detail = "هر موقع خواستی هر چی دلت خواست یادداشت کنیا" +
            "\n" + "فردا نگی یادم رفته!",
    background = R.color.white,
    isImaged = true,
    image = R.drawable.note,
    date = "همین الان"
)
val prepNote2 = Note(
    title = "یادداشت کن یادت نره!",
    detail = "هر موقع خواستی هر چی دلت خواست یادداشت کنیا" +
            "\n" + "فردا نگی یادم رفته!",
    background = R.color.white,
    isImaged = true,
    image = R.drawable.note,
    date = "همین الان"
)