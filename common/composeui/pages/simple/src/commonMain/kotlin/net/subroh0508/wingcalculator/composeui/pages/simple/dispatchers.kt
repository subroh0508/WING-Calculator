@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple

import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import net.subroh0508.wingcalculator.appeal.model.*
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.preset.model.Preset
import net.subroh0508.wingcalculator.usecase.simple.CreatePresetUseCase
import net.subroh0508.wingcalculator.usecase.simple.SearchPresetUseCase
import net.subroh0508.wingcalculator.usecase.simple.UpdatePresetUseCase

typealias SimpleCalculatorDispatcher = (SimpleCalculatorUiModel) -> Unit
typealias InputFormNameDispatcher = (String) -> Unit
typealias CreatePresetDispatcher = () -> Unit
typealias UpdatePresetDispatcher = () -> Unit

interface SearchPresetDispatcher {
    operator fun invoke(text: String?)
    operator fun invoke(query: SimpleCalculatorUiModel.Query)
    operator fun invoke(suggestion: Pair<Long, SimpleCalculatorUiModel.Form>)
}

interface InputFormDispatcher {
    operator fun invoke(vo: Int?, da: Int?, vi: Int?, me: Int?)
    operator fun invoke(index: Int, vo: Int?, da: Int?, vi: Int?)
    operator fun invoke(week: Week)
    operator fun invoke(appealRatio: AppealRatio)
    operator fun invoke(buff: Buff)
    operator fun invoke(appealJudge: AppealJudge)
    operator fun invoke(interestRatio: InterestRatio)
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

@Composable
fun provideInputFormDispatcher(): Pair<SimpleCalculatorUiModel, InputFormDispatcher> {
    val (_, uiModel) = SimpleCalculatorProviderContext.current
    val dispatcher = SimpleCalculatorDispatcherContext.current

    return uiModel to object : InputFormDispatcher {
        override fun invoke(vo: Int?, da: Int?, vi: Int?, me: Int?) = dispatcher(
            uiModel.input(
                pIdol = Idol.Produce(
                    vo?.let(::Vocal) ?: uiModel.form.pIdol.vocal,
                    da?.let(::Dance) ?: uiModel.form.pIdol.dance,
                    vi?.let(::Visual) ?: uiModel.form.pIdol.visual,
                    me ?: uiModel.form.pIdol.mental,
                ),
            )
        )

        override fun invoke(index: Int, vo: Int?, da: Int?, vi: Int?) {
            val newSIdol = uiModel.form.sIdols[index].let {
                Idol.Support(
                    vo?.let(::Vocal) ?: it.vocal,
                    da?.let(::Dance) ?: it.dance,
                    vi?.let(::Visual) ?: it.visual,
                )
            }

            dispatcher(
                uiModel.input(
                    sIdols = uiModel.form.sIdols.mapIndexed { i, sIdol -> if (i == index) newSIdol else sIdol },
                ),
            )
        }
        override fun invoke(week: Week) = dispatcher(uiModel.input(week = week))
        override fun invoke(appealRatio: AppealRatio) = dispatcher(uiModel.input(appealRatio = appealRatio))
        override fun invoke(buff: Buff) = dispatcher(uiModel.input(buff = buff))
        override fun invoke(appealJudge: AppealJudge) = dispatcher(uiModel.input(appealJudge = appealJudge))
        override fun invoke(interestRatio: InterestRatio) = dispatcher(uiModel.input(interestRatio = interestRatio))
    }
}

@Composable
fun provideInputNameDispatcher(): Pair<SimpleCalculatorUiModel, InputFormNameDispatcher> {
    val (_, uiModel) = SimpleCalculatorProviderContext.current
    val dispatcher = SimpleCalculatorDispatcherContext.current

    return uiModel to { name -> dispatcher(uiModel.inputFormName(name)) }
}

@Composable
fun provideCreatePresetDispatcher(): Pair<SimpleCalculatorUiModel, CreatePresetDispatcher> {
    val (koin, uiModel) = SimpleCalculatorProviderContext.current
    val dispatcher = SimpleCalculatorDispatcherContext.current

    val scope = rememberCoroutineScope()
    val createPresetUseCase: CreatePresetUseCase? = remember(koin) { koin?.getOrNull() }

    return uiModel to {
        val (pIdol, sIdols, _, _, _, _, _, name, comment) = uiModel.form

        if (name != null) {
            scope.launch {
                println(name)
                println(pIdol)
                val preset = createPresetUseCase?.execute(name, pIdol, sIdols, comment) ?: return@launch

                dispatcher(uiModel.copy())
            }
        }
    }
}

@Composable
fun provideUpdatePresetDispatcher(): Pair<SimpleCalculatorUiModel, UpdatePresetDispatcher> {
    val (koin, uiModel) = SimpleCalculatorProviderContext.current
    val dispatcher = SimpleCalculatorDispatcherContext.current

    val scope = rememberCoroutineScope()
    val updatePresetUseCase: UpdatePresetUseCase? = remember(koin) { koin?.getOrNull() }

    return uiModel to {
        val (id, _) = uiModel.suggests.firstOrNull() ?: (null to null)

        val (pIdol, sIdols, _, _, _, _, _, name, comment) = uiModel.form

        if (id != null && name != null) {
            scope.launch {
                val preset = updatePresetUseCase?.execute(id, name, pIdol, sIdols, comment) ?: return@launch

                dispatcher(uiModel.copy())
            }
        }
    }
}
