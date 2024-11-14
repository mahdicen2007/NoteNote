package com.example.note.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.note.R
import com.example.note.data.room.Note
import com.example.note.ui.theme.cText1
import com.example.note.ui.theme.cText3
import com.example.note.ui.theme.radius4

@Composable
fun NoteList(data: List<Note>, modifier: Modifier, onItemCLicked: (Note) -> Unit) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 24.dp)
    ) {
        items(data.size) {
            NoteView(note = data[it], onClicked = { note -> onItemCLicked.invoke(note) })
        }
    }
}

@Composable
fun NoteView(note: Note, onClicked: (Note) -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val configuration = LocalConfiguration.current
    val context = LocalContext.current

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 7.dp, bottom = 7.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClicked.invoke(note)
            }
    ) {
        val (date, pic, title) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(date) {
                    top.linkTo(pic.top)
                    start.linkTo(pic.end)
                }
                .padding(start = 18.dp, top = 8.dp),
            text = note.date,
            textAlign = TextAlign.Right,
            color = cText3,
            style = MaterialTheme.typography.overline

        )

        Text(
            modifier = Modifier
                .fillMaxWidth(if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 0.62f else 0.73f)
                .constrainAs(title) {
                    top.linkTo(date.bottom)
                    start.linkTo(pic.end)
                }
                .padding(start = 18.dp, top = 2.dp, end = 16.dp),
            text = note.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = cText1,
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Right
        )

        Image(
            painter = painterResource(R.drawable.note),
            modifier = Modifier
                .size(136.dp, 90.dp)
                .padding(start = 16.dp)
                .clip(radius4)
                .constrainAs(pic) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                },
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

    }

}
