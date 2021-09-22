package net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorDispatcherContext
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.usecase.simple.DeletePresetUseCase
import net.subroh0508.wingcalculator.usecase.simple.SearchPresetUseCase

typealias DeletePresetDispatcher = (Long) -> Unit

@Composable
fun provideDeletePresetDispatcher(): Pair<SimpleCalculatorUiModel, DeletePresetDispatcher> {
    val (koin, uiModel) = SimpleCalculatorProviderContext.current
    val dispatcher = SimpleCalculatorDispatcherContext.current

    val scope = rememberCoroutineScope()
    val deletePresetUseCase: DeletePresetUseCase? = remember(koin) { koin?.getOrNull() }

    return uiModel to { id ->
        scope.launch {
            deletePresetUseCase?.execute(id) ?: return@launch

            dispatcher(uiModel.delete(id))
        }
    }
}
