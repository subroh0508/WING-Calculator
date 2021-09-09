package net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorDispatcherContext
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.usecase.simple.CreatePresetUseCase

typealias CreatePresetDispatcher = (String?) -> Unit

@Composable
fun provideCreatePresetDispatcher(): Pair<SimpleCalculatorUiModel, CreatePresetDispatcher> {
    val (koin, uiModel) = SimpleCalculatorProviderContext.current
    val dispatcher = SimpleCalculatorDispatcherContext.current

    val scope = rememberCoroutineScope()
    val createPresetUseCase: CreatePresetUseCase? = remember(koin) { koin?.getOrNull() }

    return uiModel to { name ->
        val (pIdol, sIdols, _, _, _, _, _, _, _, comment) = uiModel.form

        dispatcher(uiModel.inputFormName(name))
        if (name != null) {
            scope.launch {
                val preset = createPresetUseCase?.execute(name, pIdol, sIdols, comment) ?: return@launch

                dispatcher(uiModel.copy())
            }
        }
    }
}
