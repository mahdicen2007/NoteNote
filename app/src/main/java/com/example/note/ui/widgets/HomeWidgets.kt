package com.example.note.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.note.R
import com.example.note.data.room.Note
import com.example.note.ui.theme.cPrimary
import com.example.note.ui.theme.cText2
import com.example.note.ui.theme.cText5
import com.example.note.util.FadeInOutWidget
import kotlinx.coroutines.delay

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
