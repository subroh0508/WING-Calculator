package net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorDispatcherContext
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.usecase.simple.FetchLatestModifiedPresetUseCase

typealias FetchLatestModifiedPresetDispatcher = suspend () -> Unit

@Composable
fun provideFetchLatestModifiedPresetUseCase(): Pair<SimpleCalculatorUiModel, FetchLatestModifiedPresetDispatcher> {
    val (koin, uiModel) = SimpleCalculatorProviderContext.current
    val dispatcher = SimpleCalculatorDispatcherContext.current

    val fetchLatestModifiedPresetUseCase: FetchLatestModifiedPresetUseCase? = remember(koin) { koin?.getOrNull() }

    return uiModel to {
        fetchLatestModifiedPresetUseCase?.execute()?.let { dispatcher(uiModel.select(it)) }
    }
}