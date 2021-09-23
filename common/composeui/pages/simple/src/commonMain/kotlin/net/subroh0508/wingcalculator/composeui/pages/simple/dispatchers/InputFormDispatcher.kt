package net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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

    val (form) = uiModel

    val handleOnProduceIdolStatusChanged = remember(uiModel) {
        { vo: Int?, da: Int?, vi: Int?, me: Int? ->
            val pIdol = Idol.Produce(
                vo?.let(::Vocal) ?: form.pIdol.vocal,
                da?.let(::Dance) ?: form.pIdol.dance,
                vi?.let(::Visual) ?: form.pIdol.visual,
                me ?: form.pIdol.mental,
            )

            dispatcher(uiModel.input(pIdol = pIdol))
        }
    }
    val handleOnSupportIdolStatusChanged = remember(uiModel) {
        { index: Int, vo: Int?, da: Int?, vi: Int?  ->
            val sIdols = form.sIdols.mapIndexed { i, sIdol ->
                if (i != index) return@mapIndexed sIdol

                Idol.Support(
                    vo?.let(::Vocal) ?: sIdol.vocal,
                    da?.let(::Dance) ?: sIdol.dance,
                    vi?.let(::Visual) ?: sIdol.visual,
                )
            }

            dispatcher(uiModel.input(sIdols = sIdols))
        }
    }

    return uiModel to object : InputFormDispatcher {
        override fun invoke(vo: Int?, da: Int?, vi: Int?, me: Int?) = handleOnProduceIdolStatusChanged(vo, da, vi, me)
        override fun invoke(index: Int, vo: Int?, da: Int?, vi: Int?) = handleOnSupportIdolStatusChanged(index, vo, da, vi)
        override fun invoke(week: Week) = dispatcher(uiModel.input(week = week))
        override fun invoke(appealRatio: AppealRatio) = dispatcher(uiModel.input(appealRatio = appealRatio))
        override fun invoke(buff: Buff) = dispatcher(uiModel.input(buff = buff))
        override fun invoke(appealJudge: AppealJudge) = dispatcher(uiModel.input(appealJudge = appealJudge))
        override fun invoke(interestRatio: InterestRatio) = dispatcher(uiModel.input(interestRatio = interestRatio))
    }
}
