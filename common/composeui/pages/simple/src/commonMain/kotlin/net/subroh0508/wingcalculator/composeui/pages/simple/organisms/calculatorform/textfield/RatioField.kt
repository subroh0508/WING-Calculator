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
import net.subroh0508.wingcalculator.composeui.pages.simple.Strings
import net.subroh0508.wingcalculator.composeui.pages.simple.getString

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
        getString(LABEL[i], buff.total()),
        buff.toString(),
        COLORS[i],
        onChange = { onChange(Buffs(i, it, buffs)) },
        modifier = modifier,
    )
}

private val LABEL = listOf(Strings.VocalBuffRatioLabel, Strings.DanceBuffRatioLabel, Strings.VisualBuffRatioLabel)
private val COLORS = listOf(vocalColor, danceColor, visualColor)

@Composable
private fun BuffRatioField(
    label: String,
    buffRatio: String,
    focusedColor: Color,
    onChange: (Buff) -> Unit,
    modifier: Modifier = Modifier,
) {
    // TODO Intialize by cache correctly
    var ratio by remember { mutableStateOf(buffRatio) }

    LaunchedEffect(ratio) { Buff(ratio)?.let(onChange) }

    ArrayNumberField(
        ratio,
        label = label,
        helperText = getString(Strings.BuffRatioHelperText),
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
        label = getString(Strings.InterestRatioLabel, total),
        helperText = getString(Strings.InterestRatioHelperText),
        regex = INTEREST_RATIO_REGEX,
        onChangeValue = { ratio = it },
        modifier = modifier,
    )
}

private val INTEREST_RATIO_REGEX = """^([0-1]?\.?[0-9]{0,2},?)*$""".toRegex()
