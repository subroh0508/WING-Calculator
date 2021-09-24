package net.subroh0508.wingcalculator.composeui.appframe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp

sealed class Pages<out T: LayoutConstraints> {
    abstract val constraints: T

    class SimpleCalculator private constructor(
        override val constraints: SimpleCalculatorPageConstraints,
    ) : Pages<SimpleCalculatorPageConstraints>() {
        constructor(maxWidth: Dp) : this(SimpleCalculatorPageConstraints(maxWidth))
    }
}

@Composable
fun rememberPage(
    maxWidth: Dp,
    init: () -> Pages<*>,
): MutableState<Pages<*>> = remember(maxWidth) { mutableStateOf(init()) }
