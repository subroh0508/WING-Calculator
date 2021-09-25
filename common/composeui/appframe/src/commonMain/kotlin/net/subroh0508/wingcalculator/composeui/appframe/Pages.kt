package net.subroh0508.wingcalculator.composeui.appframe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import net.subroh0508.wingcalculator.composeui.appframe.constraints.LayoutConstraints
import net.subroh0508.wingcalculator.composeui.appframe.constraints.SimpleCalculatorPageConstraints
import net.subroh0508.wingcalculator.composeui.appframe.constraints.invoke

sealed class Pages {
    abstract val constraints: LayoutConstraints

    class SimpleCalculator private constructor(
        override val constraints: SimpleCalculatorPageConstraints,
    ) : Pages() {
        constructor(maxWidth: Dp) : this(SimpleCalculatorPageConstraints(maxWidth))
    }
}

@Composable
fun rememberPage(
    maxWidth: Dp,
    init: () -> Pages,
) = remember(maxWidth) { mutableStateOf(init()) }
