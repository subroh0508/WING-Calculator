@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import net.subroh0508.wingcalculator.composeui.components.atoms.NumberField

@Composable
fun IdolStatusBox(
    label: String,
    onValueChange: (Int?, Int?, Int?) -> Unit,
) {
    var status by remember { mutableStateOf(IdolStatus()) }

    LaunchedEffect(status) {
        val (vocal, dance, visual) = status.toInt()

        onValueChange(vocal, dance, visual)
    }

    Column {
        Text(
            label,
            style = MaterialTheme.typography.h6,
        )
        Row {
            NumberField(
                status.vocal,
                label = { Text("Vo") },
                focusedColor = Color.Magenta,
                onChangeValue = { s -> status = status.copy(vocal = s) },
            )
            NumberField(
                status.dance,
                label = { Text("Da") },
                focusedColor = Color.Blue,
                onChangeValue = { s -> status = status.copy(dance = s) },
            )
            NumberField(
                status.visual,
                label = { Text("Vi") },
                focusedColor = Color.Yellow,
                onChangeValue = { s -> status = status.copy(dance = s) },
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
