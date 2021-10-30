@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.subroh0508.wingcalculator.composeui.components.atoms.table.EmptyTableColumn
import net.subroh0508.wingcalculator.composeui.components.atoms.table.TableColumn
import net.subroh0508.wingcalculator.composeui.components.atoms.table.TableHeader

@Composable
fun Table(
    header: List<String>,
    columns: Map<String, List<String>>,
    height: Int? = null,
    modifier: Modifier = Modifier,
) = Column(modifier) {
    TableHeader(
        header,
        modifier = Modifier.height(IntrinsicSize.Max)
            .background(MaterialTheme.colors.onSurface.copy(alpha = 0.12F)),
    )

    columns.forEach { (label, contents) -> TableColumn(label, contents) }

    (0 until getEmptySize(columns, height)).forEach { _ -> EmptyTableColumn() }
}

private fun getEmptySize(
    map: Map<String, List<String>>,
    height: Int? = null,
) = height?.let { h -> (h - map.size).takeIf { h > 0 } } ?: 0
