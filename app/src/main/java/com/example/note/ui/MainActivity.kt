package com.example.note.ui

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.note.di.myModules
import com.example.note.ui.features.HomeScreenUi
import com.example.note.ui.theme.TeamGitTheme
import com.example.note.ui.theme.cBackground
import com.example.note.util.MyScreens
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.navigation.KoinNavHost
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                Koin(
                    appDeclaration = {
                        androidContext(this@MainActivity)
                        modules(myModules)
                    }
                ) {
                    TeamGitTheme {
                        val uiController = rememberSystemUiController()
                        SideEffect {
                            uiController.setStatusBarColor(cBackground)
                        }

                        Surface(
                            color = cBackground,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            TeamGitApp()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TeamGitApp() {

    val navController = rememberNavController()
    KoinNavHost(
        navController = navController,
        startDestination = MyScreens.HomeScreen.route
    ) {

        composable(MyScreens.HomeScreen.route) {
            HomeScreenUi()
        }

    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TeamGitTheme {

    }
}