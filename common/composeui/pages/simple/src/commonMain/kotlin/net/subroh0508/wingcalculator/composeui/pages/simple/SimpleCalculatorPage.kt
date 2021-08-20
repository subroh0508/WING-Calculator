@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple

import androidx.compose.runtime.*
import net.subroh0508.wingcalculator.composeui.components.themes.AppTheme
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.composeui.pages.simple.templates.SimpleCalculatorBackdrop

typealias SimpleCalculatorDispatcher = (SimpleCalculatorUiModel) -> Unit

val SimpleCalculatorProviderContext = compositionLocalOf(
    defaultFactory = ::SimpleCalculatorUiModel,
)
val SimpleCalculatorDispatcherContext = compositionLocalOf<SimpleCalculatorDispatcher>(
    defaultFactory = { {} },
)

@Composable
fun SimpleCalculatorPage() {
    AppTheme {
        SimpleCalculatorUiModelProvider {
            SimpleCalculatorBackdrop()
        }
    }
}

@Composable
private fun SimpleCalculatorUiModelProvider(
    content: @Composable () -> Unit,
) {
    var uiModel by remember { mutableStateOf(SimpleCalculatorUiModel()) }

    CompositionLocalProvider(
        SimpleCalculatorProviderContext provides uiModel,
    ) {
        CompositionLocalProvider(
            SimpleCalculatorDispatcherContext provides { uiModel = it },
            content = content,
        )
    }
}
