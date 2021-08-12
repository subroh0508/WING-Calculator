package net.subroh0508.wingcalculator.data

import kotlin.math.floor

data class TotalAppeals(
    val toVocal: Appeal.ToVocal,
    val toDance: Appeal.ToDance,
    val toVisual: Appeal.ToVisual,
) {
    companion object {
        operator fun invoke(
            pStatus: Status,
            sStatus: List<Status>,
            week: Week,
            buff: Buff,
            appealRatio: AppealRatio,
            appealJudge: AppealJudge,
            interestRatio: InterestRatio,
        ): TotalAppeals {
            val base = BaseAppeal.Produce(pStatus, sStatus, week)

            val (vocal, dance, visual) = listOf(
                pStatus is Vocal,
                pStatus is Dance,
                pStatus is Visual,
            ).map { excellent ->
                floor(floor(floor(base * buff * appealRatio) * appealJudge.copy(excellent = excellent)) * interestRatio)
            }

            return TotalAppeals(
                Appeal.ToVocal(vocal.toInt()),
                Appeal.ToDance(dance.toInt()),
                Appeal.ToVisual(visual.toInt()),
            )
        }
    }
}
