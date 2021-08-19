@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.composeui.pages.simple.templates.SimpleCalculatorTemplate

typealias SimpleCalculatorDispatcher = (SimpleCalculatorUiModel) -> Unit

val SimpleCalculatorProviderContext = compositionLocalOf(
    defaultFactory = ::SimpleCalculatorUiModel,
)
val SimpleCalculatorDispatcherContext = compositionLocalOf<SimpleCalculatorDispatcher>(
    defaultFactory = { {} },
)

@Composable
fun SimpleCalculatorPage() {
    var uiModel by remember { mutableStateOf(SimpleCalculatorUiModel()) }

    MaterialTheme {
        CompositionLocalProvider(
            SimpleCalculatorProviderContext provides uiModel,
        ) {
            CompositionLocalProvider(
                SimpleCalculatorDispatcherContext provides { uiModel = it },
            ) {
                SimpleCalculatorTemplate()
            }
        }
    }
}
