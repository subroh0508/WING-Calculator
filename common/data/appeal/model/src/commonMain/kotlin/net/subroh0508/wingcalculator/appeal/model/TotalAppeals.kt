package net.subroh0508.wingcalculator.appeal.model

import kotlin.math.floor

data class TotalAppeals(val units: List<Unit>) {
    constructor(
        vocal: Unit = Unit(List(IDOLS_COUNT) { TotalAppeal() }),
        dance: Unit = Unit(List(IDOLS_COUNT) { TotalAppeal() }),
        visual: Unit = Unit(List(IDOLS_COUNT) { TotalAppeal() }),
    ) : this(listOf(vocal, dance, visual))

    data class Unit(private val appeals: List<TotalAppeal>) : List<TotalAppeal> by appeals

    val vocal: Unit get() = byAppealType(AppealTypeIndex.Vo)
    val dance: Unit get() = byAppealType(AppealTypeIndex.Da)
    val visual: Unit get() = byAppealType(AppealTypeIndex.Vi)

    val toVocal: Unit get() = toJudge(AppealTypeIndex.Vo)
    val toDance: Unit get() = toJudge(AppealTypeIndex.Da)
    val toVisual: Unit get() = toJudge(AppealTypeIndex.Vi)

    private fun byAppealType(index: AppealTypeIndex) = units[index.ordinal]

    private fun toJudge(index: AppealTypeIndex) = Unit(
        List(IDOLS_COUNT) { position ->
            AppealTypeIndex.values().map { units[it.ordinal][position][index.ordinal] }
        }
    )

    private enum class AppealTypeIndex { Vo, Da, Vi }

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
        private fun calculateUnitAppeals(
            pStatus: Status,
            sStatus: List<Status>,
            week: Week,
            buff: Buff,
            appealRatio: AppealRatio,
            appealJudge: AppealJudge,
            interestRatio: InterestRatio,
        ): Unit {
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

            return Unit((listOf(pTotalAppeal) + sTotalAppeals))
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
            Appeal(floor(floor(floor(base * buff * appealJudge.copy(excellent = excellent)) * appealRatio) * interestRatio).toInt())
        }

        private const val IDOLS_COUNT = 5
    }
}
