package net.subroh0508.wingcalculator.composeui.pages.simple.model

import net.subroh0508.wingcalculator.data.*

data class SimpleCalculatorUiModel(
    val pIdol: Idol.Produce = Idol.Produce(),
    val sIdols: List<Idol.Support> = listOf(Idol.Support(), Idol.Support(), Idol.Support(), Idol.Support()),
    val week: Week = Week(Week.Season.ONE),
    val appealRatio: AppealRatio = AppealRatio(2.5),
) {
    val totalAppeals: TotalAppeals = TotalAppeals(
        pIdol,
        sIdols,
        week,
        Buffs(Buff(1.0), Buff(1.0), Buff(1.0)),
        appealRatio,
        AppealJudge(AppealJudge.Factor.PERFECT),
        InterestRatio(1.0),
    )
}
