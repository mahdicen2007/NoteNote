package com.example.note.ui.features

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.viewmodel.getViewModel
import kotlin.math.log

@Composable
fun HomeScreenUi() {

    val viewModel = getViewModel<HomeViewModel>()
    val nav = getNavController()

    val data by viewModel.notes.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    if (data.isNotEmpty()) {
        Log.v("testData" , data.toString())
    }

}