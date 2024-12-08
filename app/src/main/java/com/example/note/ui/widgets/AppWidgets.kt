package com.example.note.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.note.ui.theme.cBorder
import com.example.note.ui.theme.cPrimary
import com.example.note.ui.theme.radius2

@Composable
fun MainButton(
    modifier: Modifier,
    src: Int,
    onButtonCLick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier.size(40.dp)
            .border(BorderStroke((1.7).dp , SolidColor(cBorder)) , radius2)
            .padding(0.dp)
    ) {
        Icon(
            painter = painterResource(id = src),
            contentDescription = null,
            modifier = Modifier
                .padding(4.dp)
                .size(24.dp)
                .align(Alignment.Center)
                .scale(scaleX = -1f, scaleY = 1f)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onButtonCLick.invoke()
                }
        )
    }

}