package com.example.teamgit.util

sealed class MyScreens(val route :String) {
    object HomeScreen : MyScreens("home_screen")
}