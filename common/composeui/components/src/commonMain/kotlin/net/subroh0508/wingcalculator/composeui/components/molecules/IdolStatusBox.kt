@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.molecules

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.atoms.NumberField

@Composable
fun IdolStatusBox(
    label: String,
    onValueChange: (Int?, Int?, Int?) -> Unit,
    modifier: Modifier = Modifier,
) {
    var status by remember { mutableStateOf(IdolStatus()) }

    LaunchedEffect(status) {
        val (vocal, dance, visual) = status.toInt()

        onValueChange(vocal, dance, visual)
    }

    Column(modifier = modifier) {
        val numberFieldModifier = Modifier.width(100.dp)

        Text(
            label,
            modifier = Modifier.padding(horizontal = 8.dp),
            style = MaterialTheme.typography.h6,
        )
        Row {
            NumberField(
                status.vocal,
                label = "Vo",
                focusedColor = Color.Magenta,
                onChangeValue = { s -> status = status.copy(vocal = s) },
                modifier = numberFieldModifier,
            )
            NumberField(
                status.dance,
                label = "Da",
                focusedColor = Color.Blue,
                onChangeValue = { s -> status = status.copy(dance = s) },
                modifier = numberFieldModifier,
            )
            NumberField(
                status.visual,
                label = "Vi",
                focusedColor = Color(0xFFFFA500),
                onChangeValue = { s -> status = status.copy(visual = s) },
                modifier = numberFieldModifier,
            )
        }
    }
}


private data class IdolStatus(
    val vocal: String = "",
    val dance: String = "",
    val visual: String = "",
) {
    fun toInt(): List<Int?> = listOf(vocal, dance, visual).map {
        if (it.isBlank()) {
            return@map 0
        }

        it.toIntOrNull()
    }
}
