package net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorDispatcherContext
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.usecase.simple.UpdatePresetUseCase

typealias UpdatePresetDispatcher = (String?) -> Unit

@Composable
fun provideUpdatePresetDispatcher(): Pair<SimpleCalculatorUiModel, UpdatePresetDispatcher> {
    val (koin, uiModel) = SimpleCalculatorProviderContext.current
    val dispatcher = SimpleCalculatorDispatcherContext.current

    val scope = rememberCoroutineScope()
    val updatePresetUseCase: UpdatePresetUseCase? = remember(koin) { koin?.getOrNull() }

    return uiModel to { name ->
        val (pIdol, sIdols, _, _, _, _, _, id, _, comment) = uiModel.form

        dispatcher(uiModel.inputFormName(name))
        if (id != null && name != null) {
            scope.launch {
                val preset = updatePresetUseCase?.execute(id, name, pIdol, sIdols, comment) ?: return@launch

                dispatcher(uiModel.copy())
            }
        }
    }
}
