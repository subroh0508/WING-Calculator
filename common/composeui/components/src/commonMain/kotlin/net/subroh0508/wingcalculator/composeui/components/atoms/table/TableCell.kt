@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.atoms.table

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun RowScope.TableCell(
    text: String,
    borderColor: Color = Color.Black,
) = TableCell(borderColor) {
    Text(
        text,
        textAlign = TextAlign.Center,
        modifier = Modifier.align(Alignment.Center),
    )
}

@Composable
fun RowScope.TableCell(
    borderColor: Color = Color.Black,
    text: @Composable BoxScope.() -> Unit,
) = Box(
    modifier = Modifier.fillMaxHeight()
        .weight(1F)
        .border(1.dp, borderColor),
) { text() }
