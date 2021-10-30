package net.subroh0508.wingcalculator.appeal.model

import kotlin.math.floor

data class TotalAppeals(val units: List<Unit>) {
    constructor(
        vocal: Unit = Unit(List(IDOLS_COUNT) { TotalAppeal() }),
        dance: Unit = Unit(List(IDOLS_COUNT) { TotalAppeal() }),
        visual: Unit = Unit(List(IDOLS_COUNT) { TotalAppeal() }),
    ) : this(listOf(vocal, dance, visual))

    data class Unit(private val appeals: List<TotalAppeal>) : List<TotalAppeal> by appeals.take(IDOLS_COUNT) {
        val memoryAppeals get() = appeals.last()
    }

    val vocal: Unit get() = byAppealType(AppealTypeIndex.Vo)
    val dance: Unit get() = byAppealType(AppealTypeIndex.Da)
    val visual: Unit get() = byAppealType(AppealTypeIndex.Vi)

    val toVocal: Unit get() = toJudge(AppealTypeIndex.Vo)
    val toDance: Unit get() = toJudge(AppealTypeIndex.Da)
    val toVisual: Unit get() = toJudge(AppealTypeIndex.Vi)

    val memories: List<TotalAppeal> get() = AppealTypeIndex.values().map(this@TotalAppeals::getMemoryAppeal)
    val memoryToVocal: TotalAppeal get() = getMemoryAppeal(AppealTypeIndex.Vo)
    val memoryToDance: TotalAppeal get() = getMemoryAppeal(AppealTypeIndex.Da)
    val memoryToVisual: TotalAppeal get() = getMemoryAppeal(AppealTypeIndex.Vi)

    private fun byAppealType(index: AppealTypeIndex) = units[index.ordinal]

    private fun toJudge(index: AppealTypeIndex) = Unit(
        List(IDOLS_COUNT) { position ->
            AppealTypeIndex.values().map { units[it.ordinal][position][index.ordinal] }
        }
    )

    private fun getMemoryAppeal(index: AppealTypeIndex) = units[index.ordinal].memoryAppeals

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
            memoryLevel: MemoryLevel,
        ): TotalAppeals = TotalAppeals(
            calculateUnitAppeals(
                pIdol.vocal,
                sIdols.map(Idol.Support::vocal),
                week,
                buffs.forVocal,
                appealRatio, appealJudge, interestRatio,
                memoryLevel,
            ),
            calculateUnitAppeals(
                pIdol.dance,
                sIdols.map(Idol.Support::dance),
                week,
                buffs.forDance,
                appealRatio, appealJudge, interestRatio,
                memoryLevel,
            ),
            calculateUnitAppeals(
                pIdol.visual,
                sIdols.map(Idol.Support::visual),
                week,
                buffs.forVisual,
                appealRatio, appealJudge, interestRatio,
                memoryLevel,
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
            memoryLevel: MemoryLevel,
        ): Unit {
            val pBaseAppeal = BaseAppeal.Produce(pStatus, sStatus, week)

            val pTotalAppeal = calculateAppeal(
                pStatus, pBaseAppeal,
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
            val memoryAppeal = calculateMemoryAppeal(pBaseAppeal, buff, memoryLevel)

            return Unit((listOf(pTotalAppeal) + sTotalAppeals) + listOf(memoryAppeal))
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

        private fun calculateMemoryAppeal(
            pBase: BaseAppeal.Produce,
            buff: Buff,
            memoryLevel: MemoryLevel,
        ) = MemoryJudge.values().map { judge -> Appeal(floor(floor(pBase * buff * judge) * memoryLevel).toInt()) }

        private const val IDOLS_COUNT = 5
    }
}
