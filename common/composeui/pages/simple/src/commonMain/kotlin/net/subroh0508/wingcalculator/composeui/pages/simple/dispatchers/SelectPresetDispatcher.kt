package net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorDispatcherContext
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.preset.model.Preset
import net.subroh0508.wingcalculator.usecase.simple.DeletePresetUseCase
import net.subroh0508.wingcalculator.usecase.simple.SearchPresetUseCase

typealias SelectPresetDispatcher = (Pair<Long, SimpleCalculatorUiModel.Form>) -> Unit

@Composable
fun provideSelectPresetDispatcher(): Pair<SimpleCalculatorUiModel, SelectPresetDispatcher> {
    val (koin, uiModel) = SimpleCalculatorProviderContext.current
    val dispatcher = SimpleCalculatorDispatcherContext.current

    return uiModel to { suggestion -> dispatcher(uiModel.select(suggestion)) }
}
