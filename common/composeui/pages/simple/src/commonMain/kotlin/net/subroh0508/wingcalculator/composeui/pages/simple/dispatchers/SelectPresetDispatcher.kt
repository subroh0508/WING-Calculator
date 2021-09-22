package net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers

import androidx.compose.runtime.Composable
import net.subroh0508.wingcalculator.composeui.components.di.uiModel
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorDispatcherContext
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel

typealias SelectPresetDispatcher = (Pair<Long, SimpleCalculatorUiModel.Form>) -> Unit

@Composable
fun provideSelectPresetDispatcher(): Pair<SimpleCalculatorUiModel, SelectPresetDispatcher> {
    val uiModel = SimpleCalculatorProviderContext.current.uiModel
    val dispatcher = SimpleCalculatorDispatcherContext.current

    return uiModel to { suggestion -> dispatcher(uiModel.select(suggestion)) }
}
