package net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers

import androidx.compose.runtime.*
import net.subroh0508.wingcalculator.appeal.model.BuffForm
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
            val (pIdol, sIdols, week, appealRatio, buffs, appealJudge, interestRatio, memoryLevel, id, name, comment) = uiModel.form
            val buffForm = BuffForm(week, appealRatio, buffs, appealJudge, interestRatio, memoryLevel)

            if (id != null && name != null)
                savePresetUseCase?.execute(id, name, pIdol, sIdols, buffForm, comment)
            else
                savePresetUseCase?.execute(pIdol, sIdols, buffForm, comment)

            Unit
        }
    }

    return uiModel to handleOnUiModelChanged
}
