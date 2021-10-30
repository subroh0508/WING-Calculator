@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.atoms.table

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TableHeader(
    headersText: List<String>,
    borderColor: Color = Color.Black,
    modifier: Modifier = Modifier,
) = Row(modifier) {
    Spacer(
        modifier = Modifier.fillMaxHeight()
            .weight(0.3F)
            .border(1.dp, borderColor),
    )
    headersText.forEach { text ->
        Text(
            text,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1F)
                .border(1.dp, borderColor)
                .padding(8.dp),
        )
    }
}
