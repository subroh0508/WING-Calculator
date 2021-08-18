@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.molecules.TotalAppealsTab
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.SimpleBuffForm
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.SimpleCalculateResult
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.SimpleUnitForm
import net.subroh0508.wingcalculator.data.Appeal
import net.subroh0508.wingcalculator.data.Idol
import net.subroh0508.wingcalculator.data.TotalAppeals

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

    val verticalScrollState = rememberScrollState(0)

    MaterialTheme {
        CompositionLocalProvider(
            SimpleCalculatorProviderContext provides uiModel,
        ) {
            CompositionLocalProvider(
                SimpleCalculatorDispatcherContext provides { uiModel = it },
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight()
                        .background(MaterialTheme.colors.background)
                        .verticalScroll(verticalScrollState),
                ) {
                    SimpleUnitForm()
                    SimpleBuffForm()

                    SimpleCalculateResult()
                }
            }
        }
    }
}
