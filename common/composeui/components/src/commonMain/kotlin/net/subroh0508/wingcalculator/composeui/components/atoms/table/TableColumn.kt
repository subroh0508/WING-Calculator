@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.atoms.table

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ColumnScope.TableColumn(
    label: String,
    contents: List<String>,
    borderColor: Color = Color.Black,
) = TableColumn(label, contents, borderColor = borderColor) { text -> TableCell(text, borderColor) }

@Composable
fun ColumnScope.EmptyTableColumn() = TableColumn("x", listOf(), Color.Transparent, Color.Transparent)

@Composable
fun ColumnScope.TableColumn(
    label: String,
    contents: List<String>,
    color: Color = Color.Unspecified,
    borderColor: Color = Color.Black,
    cell: @Composable RowScope.(String) -> Unit = {},
) = Row(Modifier.height(IntrinsicSize.Max)) {
    Text(
        label,
        color = color,
        textAlign = TextAlign.Center,
        modifier = Modifier.weight(0.3F)
            .border(1.dp, borderColor)
            .padding(vertical = 8.dp),
    )
    contents.forEach { content -> cell(content) }
}
