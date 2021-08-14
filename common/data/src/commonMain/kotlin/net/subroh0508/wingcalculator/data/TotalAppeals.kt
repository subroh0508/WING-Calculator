package net.subroh0508.wingcalculator.data

import kotlin.math.floor

data class TotalAppeals(
    val vocal: TotalAppeal.Vocal,
    val dance: TotalAppeal.Dance,
    val visual: TotalAppeal.Visual,
) {
    companion object {
        operator fun invoke(
            pIdol: Idol.Produce,
            sIdols: List<Idol.Support>,
            week: Week,
            buffs: Buffs,
            appealRatio: AppealRatio,
            appealJudge: AppealJudge,
            interestRatio: InterestRatio,
        ): TotalAppeals = TotalAppeals(
            calculateAppeal(
                pIdol.vocal,
                sIdols.map(Idol.Support::vocal),
                week,
                buffs.forVocal,
                appealRatio, appealJudge, interestRatio,
            ),
            calculateAppeal(
                pIdol.dance,
                sIdols.map(Idol.Support::dance),
                week,
                buffs.forDance,
                appealRatio, appealJudge, interestRatio,
            ),
            calculateAppeal(
                pIdol.visual,
                sIdols.map(Idol.Support::visual),
                week,
                buffs.forVisual,
                appealRatio, appealJudge, interestRatio,
            ),
        )

        @Suppress("UNCHECKED_CAST")
        private fun <T: TotalAppeal> calculateAppeal(
            pStatus: Status,
            sStatus: List<Status>,
            week: Week,
            buff: Buff,
            appealRatio: AppealRatio,
            appealJudge: AppealJudge,
            interestRatio: InterestRatio,
        ): T {
            val base = BaseAppeal.Produce(pStatus, sStatus, week)

            val (vocal, dance, visual) = listOf(
                pStatus is Vocal,
                pStatus is Dance,
                pStatus is Visual,
            ).map { excellent ->
                floor(floor(floor(base * buff * appealRatio) * appealJudge.copy(excellent = excellent)) * interestRatio)
            }

            val constructor = when (pStatus) {
                is Vocal -> TotalAppeal::Vocal
                is Dance -> TotalAppeal::Dance
                is Visual -> TotalAppeal::Visual
            }

            return constructor(
                Appeal.ToVocal(vocal.toInt()),
                Appeal.ToDance(dance.toInt()),
                Appeal.ToVisual(visual.toInt()),
            ) as T
        }
    }
}
