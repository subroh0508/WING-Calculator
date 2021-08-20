@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple

import androidx.compose.material.*
import androidx.compose.runtime.*
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.composeui.pages.simple.templates.SimpleCalculatorBackLayerContent
import net.subroh0508.wingcalculator.composeui.pages.simple.templates.SimpleCalculatorFrontLayerContent

typealias SimpleCalculatorDispatcher = (SimpleCalculatorUiModel) -> Unit

val SimpleCalculatorProviderContext = compositionLocalOf(
    defaultFactory = ::SimpleCalculatorUiModel,
)
val SimpleCalculatorDispatcherContext = compositionLocalOf<SimpleCalculatorDispatcher>(
    defaultFactory = { {} },
)

@Composable
fun SimpleCalculatorPage() {
    val backdropScaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed)

    MaterialTheme {
        SimpleCalculatorUiModelProvider {
            BackdropScaffold(
                appBar = {},
                backLayerContent = {
                    SimpleCalculatorBackLayerContent(
                        BackdropScaffoldDefaults.HeaderHeight,
                    )
                },
                backLayerBackgroundColor = MaterialTheme.colors.background,
                frontLayerContent = {
                    SimpleCalculatorFrontLayerContent(
                        BackdropScaffoldDefaults.HeaderHeight,
                        backdropScaffoldState,
                    )
                },
                scaffoldState = backdropScaffoldState,
            )
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
