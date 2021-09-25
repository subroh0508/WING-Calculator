package net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.DrawerType
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorDispatcherContext
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.composeui.pages.simple.model.Panels
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.usecase.simple.FetchLatestModifiedPresetUseCase

typealias LoadSimpleCalculatorUiModelDispatcher = suspend (Panels, DrawerType) -> Unit

@Composable
fun provideLoadSimpleCalculatorUiModelDispatcher(): Pair<SimpleCalculatorUiModel, LoadSimpleCalculatorUiModelDispatcher> {
    val (koin, uiModel) = SimpleCalculatorProviderContext.current
    val dispatcher = SimpleCalculatorDispatcherContext.current

    val fetchLatestModifiedPresetUseCase: FetchLatestModifiedPresetUseCase? = remember(koin) { koin?.getOrNull() }

    return uiModel to { panel, drawer ->
        fetchLatestModifiedPresetUseCase?.execute()?.let {
            dispatcher(uiModel.copy(panel = panel, drawer = drawer).select(it))
        }
    }
}