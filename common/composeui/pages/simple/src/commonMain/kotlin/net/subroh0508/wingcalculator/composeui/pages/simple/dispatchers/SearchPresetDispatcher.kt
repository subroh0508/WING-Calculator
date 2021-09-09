package net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorDispatcherContext
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.preset.model.Preset
import net.subroh0508.wingcalculator.usecase.simple.SearchPresetUseCase

interface SearchPresetDispatcher {
    operator fun invoke(text: String?)
    operator fun invoke(query: SimpleCalculatorUiModel.Query)
    operator fun invoke(suggestion: Pair<Long, SimpleCalculatorUiModel.Form>)
}

@Composable
fun provideSearchPresetDispatcher(): Pair<SimpleCalculatorUiModel, SearchPresetDispatcher> {
    val (koin, uiModel) = SimpleCalculatorProviderContext.current
    val dispatcher = SimpleCalculatorDispatcherContext.current

    val searchPresetUseCase: SearchPresetUseCase? = remember(koin) { koin?.getOrNull() }

    LaunchedEffect(uiModel.query) {
        if (searchPresetUseCase == null || uiModel.query is SimpleCalculatorUiModel.Query.Closed) {
            return@LaunchedEffect
        }

        dispatcher(
            uiModel.copy(
                suggests = searchPresetUseCase.execute(uiModel.query.text).toSuggests(),
            ),
        )
    }

    return uiModel to object : SearchPresetDispatcher {
        override fun invoke(text: String?) = dispatcher(uiModel.inputQuery(text))
        override fun invoke(query: SimpleCalculatorUiModel.Query) = dispatcher(uiModel.copy(query = query))
        override fun invoke(suggestion: Pair<Long, SimpleCalculatorUiModel.Form>) = dispatcher(uiModel.select(suggestion))
    }
}

private fun List<Preset>.toSuggests() = map {
    it.id to SimpleCalculatorUiModel.Form(
        it.pIdol,
        it.sIdols,
        name = it.name,
        comment = it.comment,
    )
}
