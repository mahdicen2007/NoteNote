package com.example.note.ui.widgets

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.note.R
import com.example.note.data.room.Note
import com.example.note.ui.theme.Shapes
import com.example.note.ui.theme.cArrow
import com.example.note.ui.theme.cBackground
import com.example.note.ui.theme.cPrimary
import com.example.note.ui.theme.cText2
import com.example.note.ui.theme.cText5
import com.example.note.util.FadeInOutWidget
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeContent(
    data: List<Note>, onAddNote: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (noNote, noteList) = createRefs()

        if (data.isEmpty()) {
            Column(
                modifier = Modifier
                    .constrainAs(noNote) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(bottom = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(120.dp),
                    painter = painterResource(R.drawable.ic_no_note),
                    contentDescription = null,
                )
                Text(
                    "یادداشتی انجام ندادی!",
                    style = MaterialTheme.typography.h5,
                    color = cText2
                )
                TextButton(onClick = { onAddNote.invoke() }) {
                    Text(
                        text = "برو یادداشت کن",
                        style = MaterialTheme.typography.caption,
                        color = cText5
                    )
                }
            }
        } else {
            NoteList(
                modifier = Modifier.constrainAs(noteList) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, data = data
            ) {
                // todo navigate to note Screen (2)
            }
        }

    }
}

@Composable
fun HomeToolbar(
    onDrawerCLicked: () -> Unit,
    onSearchClicked: () -> Unit
) {

    val textEdt = remember { mutableStateOf("") }

    ConstraintLayout(
        modifier = Modifier.run {
            fillMaxWidth()
                .height(70.dp)
                .background(cBackground)
        }
    ) {
        val (search, drawer , image) = createRefs()

        MainButton(
            modifier = Modifier.constrainAs(drawer) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start, 18.dp)
            },
            src = R.drawable.ic_list,
        ) {
            onDrawerCLicked.invoke()
        }

        Image(
            painter = painterResource(R.drawable.noteee), contentDescription = null,
            modifier = Modifier.constrainAs(image) {
                top.linkTo(drawer.top)
                bottom.linkTo(search.bottom)
                start.linkTo(drawer.start)
                end.linkTo(search.end)
            }.size(125.dp)
        )


        MainButton(
            modifier = Modifier.constrainAs(search) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end, 18.dp)
            },
            src = R.drawable.ic_search,
        ) {
            onSearchClicked.invoke()
        }

    }
}

@Composable
fun SnackBar(title: String) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(true) { isVisible = true }

    FadeInOutWidget(isVisible) {

        Snackbar(
            modifier = Modifier.padding(16.dp),
            backgroundColor = cPrimary
        ) {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        LaunchedEffect(true) {
            delay(2500)
            isVisible = false
        }

    }

}

@Composable
fun HomeDrawer(onCloseDrawer :() -> Unit) {

    BackHandler { onCloseDrawer.invoke() }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val (exit , body) = createRefs()

        MainButton(modifier = Modifier.constrainAs(exit){
            top.linkTo(parent.top , 14.dp)
            end.linkTo(parent.end , 16.dp)
        } , src = R.drawable.ic_close) {
            onCloseDrawer.invoke()
        }

        DrawerBody(modifier = Modifier.constrainAs(body){
            start.linkTo(parent.start)
            top.linkTo(exit.bottom , 29.dp)
        })

    }

}

@Composable
fun DrawerBody(modifier :Modifier) {
    Column(modifier = modifier) {
        DrawerMenuItem(
            iconDrawableId = R.drawable.ic_developer,
            text = "اطلاعات توسعه دهندگان"
        )
        DrawerMenuItem(
            iconDrawableId = R.drawable.ic_info,
            text = "درباره برنامه"
        )
    }
}

@Composable
private fun DrawerMenuItem(
    iconDrawableId :Int,
    text :String
) {

    var rotation by remember { mutableFloatStateOf(0f) }
    val scope = rememberCoroutineScope()
    val rotationAnimation = remember {Animatable(0f)}

    Column {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    scope.launch {
                        if (rotation == 0f) {
                            rotation = -90f

                            rotationAnimation.animateTo(
                                targetValue = -90f,
                                animationSpec = tween(durationMillis = 300)
                            )
                        } else {
                            rotation = 0f

                            rotationAnimation.animateTo(
                                targetValue = 0f,
                                animationSpec = tween(durationMillis = 300)
                            )
                        }
                    }
                }
                .padding(top = 14.dp, bottom = 14.dp)
        ) {

            val (title, arrow, detail) = createRefs()

            Row(
                modifier = Modifier
                    .constrainAs(title) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
                    .fillMaxWidth()
                    .padding(start = 18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(iconDrawableId),
                    tint = cPrimary,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(14.dp))
                Text(
                    text = text,
                    style = MaterialTheme.typography.subtitle2
                )
            }

            Icon(
                modifier = Modifier
                    .constrainAs(arrow) {
                        end.linkTo(parent.end, 25.dp)
                        top.linkTo(title.top)
                        bottom.linkTo(title.bottom)
                    }
                    .size(15.dp, 18.dp)
                    .rotate(rotationAnimation.value),
                tint = cArrow,
                painter = painterResource(R.drawable.ic_arrow_left),
                contentDescription = null,
            )

        }

        AnimatedVisibility(
            visible = rotation == -90f,

            enter = slideInHorizontally(
                initialOffsetX = { 30 },
                animationSpec = tween(durationMillis = 300)
            ) + fadeIn(animationSpec = tween(durationMillis = 300)),

            exit = slideOutVertically(
                targetOffsetY = { 30 },
                animationSpec = tween(durationMillis = 300)
            ) + fadeOut(animationSpec = tween(durationMillis = 300))

        ) {

            if (text == "اطلاعات توسعه دهندگان") {
//                DevelopersIds()
                Text("سلام یه همه دوستان گلم")
            } else {
                Text("سلام یه همه دوستان گلم")
//                AppInfo(
//                    modifier = Modifier.padding(
//                        top = 8.dp,
//                        start = 18.dp,
//                        end = 25.dp,
//                        bottom = 16.dp
//                    )
//                )
            }

        }

    }
}