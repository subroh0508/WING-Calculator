package net.subroh0508.wingcalculator.appeal.model

import net.subroh0508.wingcalculator.appeal.model.buffform.*

data class BuffForm(
    val week: Week,
    val appealRatio: AppealRatio,
    val buffs: Buffs,
    val appealJudge: AppealJudge,
    val interestRatio: InterestRatio,
    val memoryLevel: MemoryLevel,
)
