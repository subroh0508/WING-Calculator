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
import net.subroh0508.wingcalculator.composeui.components.themes.danceColor
import net.subroh0508.wingcalculator.composeui.components.themes.mentalColor
import net.subroh0508.wingcalculator.composeui.components.themes.visualColor
import net.subroh0508.wingcalculator.composeui.components.themes.vocalColor

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
            style = MaterialTheme.typography.subtitle1,
        )
        Row(modifier = Modifier.padding(horizontal = 4.dp)) {
            StatusField(
                status.vocal,
                label = "Vo",
                focusedColor = vocalColor,
                onChangeValue = { s -> status = status.copy(vocal = s) },
                modifier = Modifier.weight(1F),
            )
            StatusField(
                status.dance,
                label = "Da",
                focusedColor = danceColor,
                onChangeValue = { s -> status = status.copy(dance = s) },
                modifier = Modifier.weight(1F),
            )
            StatusField(
                status.visual,
                label = "Vi",
                focusedColor = visualColor,
                onChangeValue = { s -> status = status.copy(visual = s) },
                modifier = Modifier.weight(1F),
            )
            StatusField(
                status.mental,
                label = "Me",
                focusedColor = mentalColor,
                onChangeValue = { s -> status = status.copy(mental = s) },
                modifier = Modifier.weight(1F),
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
    modifier: Modifier = Modifier,
) = NumberField(
    value,
    label = label,
    focusedColor = focusedColor,
    regex = STATUS_NUMBER_REGEX,
    onChangeValue = onChangeValue,
    modifier = modifier.padding(horizontal = 4.dp),
)

private data class IdolStatus(
    val vocal: String = "",
    val dance: String = "",
    val visual: String = "",
    val mental: String = "",
) {
    fun toInt(): List<Int?> = listOf(vocal, dance, visual, mental).map {
        if (it.isBlank()) {
            return@map 0
        }

        it.toIntOrNull()
    }
}
