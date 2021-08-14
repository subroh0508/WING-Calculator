@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.atoms

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


private val JUDGES = listOf("Vo審査員", "Da審査員", "Vi審査員")
private val HEADERS = listOf("P", "S1", "S2", "S3", "S4")

@Composable
fun TotalAppealsTable(
    appeals: List<List<String>>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        val cellModifier = Modifier.width(72.dp)
            .padding(8.dp)

        Row {
            Spacer(modifier = Modifier.width(100.dp))
            HEADERS.forEach { header ->
                Text(header, textAlign = TextAlign.Center, modifier = cellModifier)
            }
        }

        appeals.forEachIndexed { i, rows ->
            Row {
                Text(
                    JUDGES[i],
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.End,
                    modifier = Modifier.width(100.dp)
                        .padding(end = 8.dp),
                )
                rows.forEach { appeal ->
                    Text(appeal, textAlign = TextAlign.Center, modifier = cellModifier)
                }
            }
        }
    }
}
