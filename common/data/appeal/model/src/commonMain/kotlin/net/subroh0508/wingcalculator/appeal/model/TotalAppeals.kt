package net.subroh0508.wingcalculator.appeal.model

import kotlin.math.floor

private const val IDOLS_COUNT = 5

data class TotalAppeals(
    val vocal: Unit<TotalAppeal.Vocal> = Unit(List(IDOLS_COUNT) { TotalAppeal.Vocal() }),
    val dance: Unit<TotalAppeal.Dance> = Unit(List(IDOLS_COUNT) { TotalAppeal.Dance() }),
    val visual: Unit<TotalAppeal.Visual> = Unit(List(IDOLS_COUNT) { TotalAppeal.Visual() }),
) {
    data class Unit<out T: TotalAppeal>(private val appeals: List<T>) : List<T> by appeals

    val toVocal: List<List<Appeal.ToVocal>> get() = toJudge(TotalAppeal::toVocal)
    val toDance: List<List<Appeal.ToDance>> get() = toJudge(TotalAppeal::toDance)
    val toVisual: List<List<Appeal.ToVisual>> get() = toJudge(TotalAppeal::toVisual)

    private fun <T: Appeal> toJudge(transform: (TotalAppeal) -> T) = (0 until IDOLS_COUNT).map { i ->
        listOf(vocal[i], dance[i], visual[i]).map(transform)
    }

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
            calculateUnitAppeals(
                pIdol.vocal,
                sIdols.map(Idol.Support::vocal),
                week,
                buffs.forVocal,
                appealRatio, appealJudge, interestRatio,
            ),
            calculateUnitAppeals(
                pIdol.dance,
                sIdols.map(Idol.Support::dance),
                week,
                buffs.forDance,
                appealRatio, appealJudge, interestRatio,
            ),
            calculateUnitAppeals(
                pIdol.visual,
                sIdols.map(Idol.Support::visual),
                week,
                buffs.forVisual,
                appealRatio, appealJudge, interestRatio,
            ),
        )

        @Suppress("UNCHECKED_CAST")
        private fun <T: TotalAppeal> calculateUnitAppeals(
            pStatus: Status,
            sStatus: List<Status>,
            week: Week,
            buff: Buff,
            appealRatio: AppealRatio,
            appealJudge: AppealJudge,
            interestRatio: InterestRatio,
        ): Unit<T> {
            val pTotalAppeal = calculateAppeal(
                pStatus, BaseAppeal.Produce(pStatus, sStatus, week),
                buff, appealRatio, appealJudge, interestRatio,
            )
            val sTotalAppeals = sStatus.mapIndexed { index, s ->
                calculateAppeal(
                    s, BaseAppeal.Support(
                        listOf(s) + sStatus.toMutableList().apply { removeAt(index) },
                        pStatus,
                        week,
                    ),
                    buff, appealRatio, appealJudge, interestRatio,
                )
            }

            val constructor = when (pStatus) {
                is Vocal -> TotalAppeal::Vocal
                is Dance -> TotalAppeal::Dance
                is Visual -> TotalAppeal::Visual
            }

            return Unit(
                (listOf(pTotalAppeal) + sTotalAppeals).map { (vocal, dance, visual) ->
                    constructor(
                        Appeal.ToVocal(vocal),
                        Appeal.ToDance(dance),
                        Appeal.ToVisual(visual),
                    ) as T
                },
            )
        }

        private fun calculateAppeal(
            status: Status,
            base: BaseAppeal,
            buff: Buff,
            appealRatio: AppealRatio,
            appealJudge: AppealJudge,
            interestRatio: InterestRatio,
        ) = listOf(
                status is Vocal,
                status is Dance,
                status is Visual,
        ).map { excellent ->
            floor(floor(floor(base * buff * appealJudge.copy(excellent = excellent)) * appealRatio) * interestRatio).toInt()
        }
    }
}
