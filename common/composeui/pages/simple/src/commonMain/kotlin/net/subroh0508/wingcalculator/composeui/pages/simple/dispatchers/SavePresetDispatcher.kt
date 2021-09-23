package net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers

import androidx.compose.runtime.*
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorDispatcherContext
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.usecase.simple.UpdatePresetUseCase

typealias SavePresetDispatcher = suspend () -> Unit

@Composable
fun provideSavePresetDispatcher(): Pair<SimpleCalculatorUiModel, SavePresetDispatcher> {
    val (koin, uiModel) = SimpleCalculatorProviderContext.current
    val dispatcher = SimpleCalculatorDispatcherContext.current

    val updatePresetUseCase: UpdatePresetUseCase? = remember(koin) { koin?.getOrNull() }

    val handleOnUiModelChanged = remember(uiModel) {
        suspend {
            val (pIdol, sIdols, _, _, _, _, _, id, name, comment) = uiModel.form

            if (id != null && name != null) {
                updatePresetUseCase?.execute(id, name, pIdol, sIdols, comment)?.let {
                    dispatcher(uiModel.select(it))
                }
            }
        }
    }

    return uiModel to handleOnUiModelChanged
}
