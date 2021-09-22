package net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers

import androidx.compose.runtime.Composable
import net.subroh0508.wingcalculator.appeal.model.*
import net.subroh0508.wingcalculator.composeui.components.di.uiModel
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorDispatcherContext
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel

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
fun provideInputFormDispatcher(): Pair<SimpleCalculatorUiModel, InputFormDispatcher> {
    val uiModel = SimpleCalculatorProviderContext.current.uiModel
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
