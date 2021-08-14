package net.subroh0508.wingcalculator.composeui.pages.simple.model

import net.subroh0508.wingcalculator.data.*

data class SimpleCalculatorUiModel(
    val pIdol: Idol.Produce = Idol.Produce(),
    val sIdols: List<Idol.Support> = listOf(Idol.Support(), Idol.Support(), Idol.Support(), Idol.Support()),
) {
    val totalAppeals: TotalAppeals = TotalAppeals(
        pIdol,
        sIdols,
        Week(Week.Season.FINAL),
        Buffs(Buff(1.0), Buff(1.0), Buff(1.0)),
        AppealRatio(2.5),
        AppealJudge(AppealJudge.Factor.PERFECT),
        InterestRatio(1.0),
    )
}
