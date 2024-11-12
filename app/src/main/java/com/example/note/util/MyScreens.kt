package com.example.note.util

sealed class MyScreens(val route :String) {
    object HomeScreen : MyScreens("home_screen")
}