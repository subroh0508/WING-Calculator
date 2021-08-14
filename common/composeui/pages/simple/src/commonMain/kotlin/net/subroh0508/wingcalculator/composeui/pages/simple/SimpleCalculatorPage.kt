@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import net.subroh0508.wingcalculator.composeui.pages.simple.container.SimpleUnitForm
import net.subroh0508.wingcalculator.data.Idol
import net.subroh0508.wingcalculator.data.TotalAppeals

data class SimpleCalculatorUiModel(
    val pIdol: Idol.Produce = Idol.Produce(),
    val sIdols: List<Idol.Support> = listOf(Idol.Support(), Idol.Support(), Idol.Support(), Idol.Support()),
    val totalAppeals: TotalAppeals = TotalAppeals(),
)

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
                Column {
                    SimpleUnitForm()
                    Text(uiModel.toString())
                }
            }
        }
    }
}
