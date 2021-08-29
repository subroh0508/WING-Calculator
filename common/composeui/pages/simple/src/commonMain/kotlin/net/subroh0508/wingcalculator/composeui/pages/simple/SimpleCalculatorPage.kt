@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple

import androidx.compose.runtime.*
import net.subroh0508.wingcalculator.composeui.components.di.KoinComponent
import net.subroh0508.wingcalculator.composeui.components.di.getKoin
import net.subroh0508.wingcalculator.composeui.components.themes.AppTheme
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.composeui.pages.simple.templates.SimpleCalculatorDrawer
import net.subroh0508.wingcalculator.core.pages.SimpleCalculatorModule

typealias SimpleCalculatorDispatcher = (SimpleCalculatorUiModel) -> Unit

val SimpleCalculatorProviderContext = compositionLocalOf(
    defaultFactory = ::SimpleCalculatorUiModel,
)
val SimpleCalculatorDispatcherContext = compositionLocalOf<SimpleCalculatorDispatcher>(
    defaultFactory = { {} },
)

@Composable
fun SimpleCalculatorPage() = KoinComponent(SimpleCalculatorModule) {
    // Module読み込んだらProviderで流すようにしたい
    AppTheme {
        SimpleCalculatorUiModelProvider {
            SimpleCalculatorDrawer()
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
