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

private val STATUS_NUMBER_REGEX = """^(0|[1-9][0-9]{0,4})*$""".toRegex()

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
        Text(
            label,
            modifier = Modifier.padding(horizontal = 8.dp),
            style = MaterialTheme.typography.h6,
        )
        Row {
            StatusField(
                status.vocal,
                label = "Vo",
                focusedColor = Color.Magenta,
                onChangeValue = { s -> status = status.copy(vocal = s) },
            )
            StatusField(
                status.dance,
                label = "Da",
                focusedColor = Color.Blue,
                onChangeValue = { s -> status = status.copy(dance = s) },
            )
            StatusField(
                status.visual,
                label = "Vi",
                focusedColor = Color(0xFFFFA500),
                onChangeValue = { s -> status = status.copy(visual = s) },
            )
        }
    }
}

@Composable
private fun StatusField(
    value: String,
    label: String,
    focusedColor: Color,
    onChangeValue: (String) -> Unit,
) = NumberField(
    value,
    label = label,
    focusedColor = focusedColor,
    regex = STATUS_NUMBER_REGEX,
    onChangeValue = onChangeValue,
    modifier = Modifier.width(100.dp)
        .padding(horizontal = 8.dp),
)

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
