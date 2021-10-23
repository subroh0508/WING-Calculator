@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.atoms

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

private val JUDGES = listOf("Vo審査員", "Da審査員", "Vi審査員")
private val HEADERS = listOf("P", "S1", "S2", "S3", "S4")

@Composable
fun TotalAppealsTable(
    appeals: List<List<String>>,
    modifier: Modifier = Modifier,
) {
    val contents = HEADERS.foldIndexed(mapOf<String, List<String>>()) { i, acc, label ->
        acc + mapOf(label to appeals[i])
    }

    Table(JUDGES, contents, modifier)
}
