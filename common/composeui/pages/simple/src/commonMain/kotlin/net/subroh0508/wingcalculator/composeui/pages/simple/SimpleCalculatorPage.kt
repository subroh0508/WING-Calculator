@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.*
import androidx.compose.runtime.*
import net.subroh0508.wingcalculator.composeui.components.atoms.StaticBackdrop
import net.subroh0508.wingcalculator.composeui.components.themes.AppTheme
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.RESULT_COMPONENT_HEIGHT
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
    AppTheme {
        SimpleCalculatorUiModelProvider {
            BoxWithConstraints {
                StaticBackdrop(
                    appBar = {},
                    backLayerContent = {
                        SimpleCalculatorBackLayerContent(
                            RESULT_COMPONENT_HEIGHT + BackdropScaffoldDefaults.HeaderHeight,
                        )
                    },
                    backLayerBackgroundColor = MaterialTheme.colors.background,
                    frontLayerContent = {
                        SimpleCalculatorFrontLayerContent(BackdropScaffoldDefaults.HeaderHeight)
                    },
                )
            }
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
