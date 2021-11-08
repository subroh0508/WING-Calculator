@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculatorform.textfield

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.appeal.model.buffform.Buff
import net.subroh0508.wingcalculator.appeal.model.buffform.Buffs
import net.subroh0508.wingcalculator.composeui.components.atoms.textfield.ArrayNumberField
import net.subroh0508.wingcalculator.composeui.components.themes.danceColor
import net.subroh0508.wingcalculator.composeui.components.themes.visualColor
import net.subroh0508.wingcalculator.composeui.components.themes.vocalColor

@Composable
fun ColumnScope.BuffRatioFields(
    buffs: Buffs,
    onChange: (Buffs) -> Unit,
    modifier: Modifier = Modifier,
) = buffs.forEachIndexed { i, buff ->
    if (i != 0) {
        Spacer(Modifier.height(16.dp))
    }
    BuffRatioField(
        LABEL[i],
        buff.toString(),
        buff.total(),
        COLORS[i],
        onChange = { onChange(Buffs(i, it, buffs)) },
        modifier = modifier,
    )
}

private val LABEL = listOf("Voバフ補正", "Daバフ補正", "Viバフ補正")
private val COLORS = listOf(vocalColor, danceColor, visualColor)

@Composable
private fun BuffRatioField(
    label: String,
    buffRatio: String,
    total: String,
    focusedColor: Color,
    onChange: (Buff) -> Unit,
    modifier: Modifier = Modifier,
) {
    var ratio by remember { mutableStateOf(buffRatio) }

    LaunchedEffect(ratio) { Buff(ratio)?.let(onChange) }

    ArrayNumberField(
        ratio,
        label = "$label: $total",
        helperText = "カンマ区切り(単位: %)",
        regex = BUFF_RATIO_REGEX,
        focusedColor = focusedColor,
        onChangeValue = { ratio = it },
        modifier = modifier,
    )
}

private val BUFF_RATIO_REGEX = """^((0|-?[1-9]?[0-9]?|-?100),?)*$""".toRegex()

@Composable
fun InterestRatioField(
    interestRatio: String,
    total: String,
    onChange: (List<Double>) -> Unit,
    modifier: Modifier = Modifier,
) {
    var ratio by remember { mutableStateOf(interestRatio) }

    LaunchedEffect(ratio) {
        val array = ratio.split(",")
            .filter(String::isNotBlank)
            .mapNotNull { it.toDoubleOrNull()?.takeIf { r -> r > 0.0 } }

        if (array.isNotEmpty()) onChange(array)
    }

    ArrayNumberField(
        ratio,
        label = "興味度: $total",
        helperText = "カンマ区切り",
        regex = INTEREST_RATIO_REGEX,
        onChangeValue = { ratio = it },
        modifier = modifier,
    )
}

private val INTEREST_RATIO_REGEX = """^([0-1]?\.?[0-9]{0,2},?)*$""".toRegex()
