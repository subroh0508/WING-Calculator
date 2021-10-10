@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
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
        val cellModifier = Modifier.weight(1F)
            .border(1.dp, Color.Black)
            .padding(8.dp)

        Row(
            modifier = Modifier.height(IntrinsicSize.Max)
                .background(MaterialTheme.colors.onSurface.copy(alpha = 0.12F)),
        ) {
            Spacer(
                modifier = Modifier.fillMaxHeight()
                    .weight(0.3F)
                    .border(1.dp, Color.Black),
            )
            JUDGES.forEach { judge ->
                Text(judge, textAlign = TextAlign.Center, modifier = cellModifier)
            }
        }

        appeals.forEachIndexed { i, rows ->
            Row(modifier = Modifier.height(IntrinsicSize.Max)) {
                Text(
                    HEADERS[i],
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.3F)
                        .border(1.dp, Color.Black)
                        .padding(vertical = 8.dp),
                )
                rows.forEach { appeal ->
                    Box(
                        modifier = Modifier.fillMaxHeight()
                            .weight(1F)
                            .border(1.dp, Color.Black),
                    ) {
                        Text(
                            appeal,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center),
                        )
                    }
                }
            }
        }
    }
}
