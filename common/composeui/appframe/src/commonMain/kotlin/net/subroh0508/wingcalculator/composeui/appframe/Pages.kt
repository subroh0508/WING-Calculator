package net.subroh0508.wingcalculator.composeui.appframe

import androidx.compose.runtime.*
import androidx.compose.ui.unit.Dp
import net.subroh0508.wingcalculator.composeui.appframe.constraints.LayoutConstraints
import net.subroh0508.wingcalculator.composeui.appframe.constraints.AppPreferencePageConstraints
import net.subroh0508.wingcalculator.composeui.appframe.constraints.SimpleCalculatorPageConstraints
import net.subroh0508.wingcalculator.composeui.appframe.constraints.invoke

sealed class Pages {
    abstract val constraints: LayoutConstraints

    class SimpleCalculator private constructor(
        override val constraints: SimpleCalculatorPageConstraints,
    ) : Pages() {
        constructor(maxWidth: Dp) : this(SimpleCalculatorPageConstraints(maxWidth))
    }

    class AppPreference private constructor(
        override val constraints: AppPreferencePageConstraints,
    ) : Pages() {
        constructor(maxWidth: Dp) : this(AppPreferencePageConstraints(maxWidth))
    }

    companion object {
        operator fun invoke(pages: Pages, maxWidth: Dp) = when (pages) {
            is SimpleCalculator -> SimpleCalculator(maxWidth)
            is AppPreference -> AppPreference(maxWidth)
        }
    }
}

class PageController(
    private val maxWidth: Dp,
    private val onChange: (Pages) -> Unit,
) {
    fun openSimpleCalculator() = onChange(Pages.SimpleCalculator(maxWidth))
    fun openAppPreference() = onChange(Pages.AppPreference(maxWidth))
}

@Composable
fun providePageController(
    maxWidth: Dp,
    home: Pages = Pages.SimpleCalculator(maxWidth),
): Pair<Pages, PageController> {
    var page: Pages by remember { mutableStateOf(home) }

    LaunchedEffect(maxWidth) { page = Pages(page, maxWidth) }

    return page to PageController(maxWidth) { page = it }
}
