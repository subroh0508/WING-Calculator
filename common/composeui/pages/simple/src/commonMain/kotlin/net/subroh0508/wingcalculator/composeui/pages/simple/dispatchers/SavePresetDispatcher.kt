package net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers

import androidx.compose.runtime.*
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorDispatcherContext
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.usecase.simple.SavePresetUseCase

typealias SavePresetDispatcher = suspend () -> Unit

@Composable
fun provideSavePresetDispatcher(): Pair<SimpleCalculatorUiModel, SavePresetDispatcher> {
    val (koin, uiModel) = SimpleCalculatorProviderContext.current

    val savePresetUseCase: SavePresetUseCase? = remember(koin) { koin?.getOrNull() }

    val handleOnUiModelChanged = remember(uiModel) {
        suspend {
            val (pIdol, sIdols, _, _, _, _, _, id, name, comment) = uiModel.form

            if (id != null && name != null)
                savePresetUseCase?.execute(id, name, pIdol, sIdols, comment)
            else
                savePresetUseCase?.execute(pIdol, sIdols, comment)

            Unit
        }
    }

    return uiModel to handleOnUiModelChanged
}
