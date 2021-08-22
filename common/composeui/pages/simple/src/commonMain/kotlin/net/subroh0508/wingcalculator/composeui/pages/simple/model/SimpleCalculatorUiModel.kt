package net.subroh0508.wingcalculator.composeui.pages.simple.model

import net.subroh0508.wingcalculator.data.*

data class SimpleCalculatorUiModel(
    val form: Form = Form(),
    val query: String? = null,
    val suggests: List<Pair<String, Form>> = listOf(),
) {
    val totalAppeals = form.let { (pIdol, sIdols, week, appealRatio, buff, appealJudge, interestRatio) ->
        TotalAppeals(
            pIdol,
            sIdols,
            week,
            Buffs(buff, buff, buff),
            appealRatio,
            appealJudge,
            interestRatio,
        )
    }

    fun input(
        pIdol: Idol.Produce = form.pIdol,
        sIdols: List<Idol.Support> = form.sIdols,
        week: Week = form.week,
        appealRatio: AppealRatio = form.appealRatio,
        buff: Buff = form.buff,
        appealJudge: AppealJudge = form.appealJudge,
        interestRatio: InterestRatio = form.interestRatio,
    ) = copy(
        form = Form(
            pIdol,
            sIdols,
            week,
            appealRatio,
            buff,
            appealJudge,
            interestRatio,
        ),
    )

    data class Form(
        val pIdol: Idol.Produce = Idol.Produce(),
        val sIdols: List<Idol.Support> = listOf(Idol.Support(), Idol.Support(), Idol.Support(), Idol.Support()),
        val week: Week = Week(Week.Season.ONE),
        val appealRatio: AppealRatio = AppealRatio(2.5),
        val buff: Buff = Buff(listOf(0.0)),
        val appealJudge: AppealJudge = AppealJudge(AppealJudge.Factor.GOOD),
        val interestRatio: InterestRatio = InterestRatio(listOf(1.0)),
    )
}
