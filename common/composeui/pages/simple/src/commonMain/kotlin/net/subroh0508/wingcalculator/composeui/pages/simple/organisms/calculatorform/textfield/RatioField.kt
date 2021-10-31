@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculatorform.textfield

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import net.subroh0508.wingcalculator.composeui.components.atoms.textfield.ArrayNumberField

@Composable
fun BuffRatioField(
    buffRatio: String,
    total: String,
    onChange: (List<Double>) -> Unit,
    modifier: Modifier = Modifier,
) {
    var ratio by remember { mutableStateOf(buffRatio) }

    LaunchedEffect(ratio) {
        val array = ratio.split(",")
            .filter(String::isNotBlank)
            .mapNotNull { it.toIntOrNull()?.let { n -> n * 0.01 } }

        if (array.isNotEmpty()) onChange(array)
    }

    ArrayNumberField(
        ratio,
        label = "バフ補正: $total",
        helperText = "カンマ区切り(単位: %)",
        regex = BUFF_RATIO_REGEX,
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
