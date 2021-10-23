@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.subroh0508.wingcalculator.composeui.components.atoms.table.TableColumn
import net.subroh0508.wingcalculator.composeui.components.atoms.table.TableHeader

private val JUDGES = listOf("Vo審査員", "Da審査員", "Vi審査員")
private val HEADERS = listOf("P", "S1", "S2", "S3", "S4")

@Composable
fun TotalAppealsTable(
    appeals: List<List<String>>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        TableHeader(
            JUDGES,
            modifier = Modifier.height(IntrinsicSize.Max)
                .background(MaterialTheme.colors.onSurface.copy(alpha = 0.12F)),
        )

        appeals.forEachIndexed { i, rows -> TableColumn(HEADERS[i], rows) }
    }
}
