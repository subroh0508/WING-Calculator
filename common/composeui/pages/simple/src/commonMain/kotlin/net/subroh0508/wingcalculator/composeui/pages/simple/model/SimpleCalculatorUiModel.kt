package net.subroh0508.wingcalculator.composeui.pages.simple.model

import net.subroh0508.wingcalculator.data.*

data class SimpleCalculatorUiModel(
    val pIdol: Idol.Produce = Idol.Produce(),
    val sIdols: List<Idol.Support> = listOf(Idol.Support(), Idol.Support(), Idol.Support(), Idol.Support()),
    val week: Week = Week(Week.Season.ONE),
    val appealRatio: AppealRatio = AppealRatio(2.5),
    val buff: Buff = Buff(listOf(0.0)),
    val appealJudge: AppealJudge = AppealJudge(AppealJudge.Factor.GOOD),
    val interestRatio: InterestRatio = InterestRatio(listOf(1.0)),
) {
    val totalAppeals: TotalAppeals = TotalAppeals(
        pIdol,
        sIdols,
        week,
        Buffs(buff, buff, buff),
        appealRatio,
        appealJudge,
        interestRatio,
    )
}
