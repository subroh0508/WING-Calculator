package net.subroh0508.wingcalculator.composeui.pages.simple.model

import net.subroh0508.wingcalculator.data.*

data class SimpleCalculatorUiModel(
    val form: Form = Form(),
    val query: Query = Query.Closed,
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

    fun input(query: String?) = if (this.query is Query.Closed) this else copy(query = Query.Opened(query))

    fun select(suggest: Pair<String, Form>) = copy(
        query = Query.Closed,
        suggests = listOf(suggest),
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

    sealed class Query {
        abstract val text: String?

        object Closed : Query() { override val text: String? = null }
        data class Opened(override val text: String? = null) : Query()
    }
}
