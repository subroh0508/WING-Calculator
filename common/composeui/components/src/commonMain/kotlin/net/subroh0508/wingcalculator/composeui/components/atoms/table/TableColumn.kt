@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.atoms.table

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ColumnScope.TableColumn(
    label: String,
    contents: List<String>,
) = TableColumn(label, contents) { text -> TableCell(text) }

@Composable
fun ColumnScope.TableColumn(
    label: String,
    contents: List<String>,
    cell: @Composable RowScope.(String) -> Unit,
) = Row(Modifier.height(IntrinsicSize.Max)) {
    Text(
        label,
        textAlign = TextAlign.Center,
        modifier = Modifier.weight(0.3F)
            .border(1.dp, Color.Black)
            .padding(vertical = 8.dp),
    )
    contents.forEach { content -> cell(content) }
}
