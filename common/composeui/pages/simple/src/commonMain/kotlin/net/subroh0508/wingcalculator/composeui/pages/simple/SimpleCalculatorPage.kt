@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple

import androidx.compose.runtime.*
import net.subroh0508.wingcalculator.composeui.components.di.KoinComponent
import net.subroh0508.wingcalculator.composeui.components.themes.AppTheme
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.composeui.pages.simple.templates.SimpleCalculatorDrawer
import net.subroh0508.wingcalculator.usecase.simple.di.SimpleCalculatorDomainModule
import org.koin.core.Koin

val SimpleCalculatorProviderContext = compositionLocalOf(
    defaultFactory = { Pair<Koin?, SimpleCalculatorUiModel>(null, SimpleCalculatorUiModel()) },
)
val SimpleCalculatorDispatcherContext = compositionLocalOf<SimpleCalculatorDispatcher>(
    defaultFactory = { {} },
)

@Composable
fun SimpleCalculatorPage() {
    AppTheme {
        SimpleCalculatorContainer(SimpleCalculatorUiModel()) {
            SimpleCalculatorDrawer()
        }
    }
}

@Composable
private fun SimpleCalculatorContainer(
    initUiModel: SimpleCalculatorUiModel,
    content: @Composable () -> Unit,
) {
    var uiModel by remember { mutableStateOf(initUiModel) }

    KoinComponent(SimpleCalculatorDomainModule) {
        CompositionLocalProvider(
            SimpleCalculatorDispatcherContext provides { uiModel = it },
        ) {
            CompositionLocalProvider(
                SimpleCalculatorProviderContext provides (it to uiModel),
                content = content,
            )
        }
    }
}
