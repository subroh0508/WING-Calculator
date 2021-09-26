package net.subroh0508.wingcalculator.composeui.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

fun Modifier.borderEnd(color: Color, width: Float) = drawBehind {
    drawLine(
        color,
        start = Offset(size.width, 0F),
        end = Offset(size.width, size.height),
        strokeWidth = width,
    )
}
