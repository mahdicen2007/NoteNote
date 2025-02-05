package com.example.note.ui.features

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.note.ui.theme.cBackground
import com.example.note.ui.widgets.DrawerBody
import com.example.note.ui.widgets.HomeContent
import com.example.note.ui.widgets.HomeDrawer
import com.example.note.ui.widgets.HomeToolbar
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.viewmodel.getViewModel
import kotlinx.coroutines.launch
import kotlin.math.log

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreenUi() {

    val activity = (LocalContext.current as? Activity)
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val viewModel = getViewModel<HomeViewModel>()
    val nav = getNavController()

    val data by viewModel.notes.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    if (data.isNotEmpty()) {
        Log.v("testData", data.toString())
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeToolbar(
                onSearchClicked = {},
                onDrawerCLicked = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
        drawerGesturesEnabled = true, // to open drawer by finger without tapping any button,
        drawerContent = {
            HomeDrawer {
                scope.launch {
                    if(scaffoldState.drawerState.currentValue == DrawerValue.Open){
                        scaffoldState.drawerState.close()
                    } else {
                        activity?.finish()
                    }

                }
            }
        },
        drawerElevation = 4.dp,
        drawerBackgroundColor = cBackground
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            if (isLoading) {

                // to show progress bar
                CircularProgressIndicator(
                    modifier = Modifier.size(40.dp)
                        .align(Alignment.Center)
                )

            } else {

                HomeContent(data) {
                    viewModel.getAllNotes()
                }

            }
        }

    }

}