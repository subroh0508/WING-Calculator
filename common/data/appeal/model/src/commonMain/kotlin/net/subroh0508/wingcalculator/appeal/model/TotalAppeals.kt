package net.subroh0508.wingcalculator.appeal.model

import net.subroh0508.wingcalculator.appeal.model.internal.AppealTypeIndex
import kotlin.math.floor

data class TotalAppeals internal constructor(val units: List<Unit>) {
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

    private fun byAppealType(index: AppealTypeIndex) = units[index.ordinal]

    private fun toJudge(index: AppealTypeIndex) = Unit(
        List(IDOLS_COUNT) { position ->
            AppealTypeIndex.values().map { units[it.ordinal][position][index.ordinal] }
        }
    )

    private fun getMemoryAppeal(index: AppealTypeIndex) = units[index.ordinal].memoryAppeals

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
        ) = calculateBaseAppeals(pIdol, sIdols, week).let { baseAppeals ->
            TotalAppeals(
                calculateUnitAppeals(
                    pIdol,
                    baseAppeals,
                    buffs,
                    appealRatio, appealJudge, interestRatio,
                    calculateMemoryAppeal(baseAppeals, buffs, memoryLevel),
                ),
            )
        }

        private fun calculateBaseAppeals(
            pIdol: Idol.Produce,
            sIdols: List<Idol.Support>,
            week: Week,
        ) = listOf(
                pIdol.vocal to sIdols.map(Idol.Support::vocal),
                pIdol.dance to sIdols.map(Idol.Support::dance),
                pIdol.visual to sIdols.map(Idol.Support::visual),
        ).map { (pIdol, sIdols) ->
            BaseAppeal.Produce(pIdol, sIdols, week) to sIdols.mapIndexed { index, s ->
                BaseAppeal.Support(
                    listOf(s) + sIdols.toMutableList().apply { removeAt(index) },
                    pIdol,
                    week,
                )
            }
        }

        @Suppress("UNCHECKED_CAST")
        private fun calculateUnitAppeals(
            pIdol: Idol.Produce,
            baseAppeals: List<Pair<BaseAppeal.Produce, List<BaseAppeal.Support>>>,
            buffs: Buffs,
            appealRatio: AppealRatio,
            appealJudge: AppealJudge,
            interestRatio: InterestRatio,
            memoryAppeals: List<List<Appeal>>,
        ) = listOf(
            pIdol.vocal to buffs.forVocal,
            pIdol.dance to buffs.forDance,
            pIdol.visual to buffs.forVisual,
        ).mapIndexed { i, (pStatus, buff) ->
            val (pBaseAppeal, sBaseAppeal) = baseAppeals[i]

            val pTotalAppeal = calculateAppeal(
                pStatus, pBaseAppeal,
                buff, appealRatio, appealJudge, interestRatio,
            )
            val sTotalAppeals = sBaseAppeal.map { base ->
                calculateAppeal(
                    pStatus, base,
                    buff, appealRatio, appealJudge, interestRatio,
                )
            }

            Unit((listOf(pTotalAppeal) + sTotalAppeals) + listOf(memoryAppeals[i]))
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
            baseAppeals: List<Pair<BaseAppeal.Produce, List<BaseAppeal.Support>>>,
            buffs: Buffs,
            memoryLevel: MemoryLevel,
        ) = baseAppeals.fold(List(3) { MutableList(2) { Appeal(0) } }) { appeals, (baseAppeal, _) ->
            listOf(
                (if (baseAppeal.pStatus is Vocal) 2 else 1) to buffs.forVocal,
                (if (baseAppeal.pStatus is Dance) 2 else 1) to buffs.forDance,
                (if (baseAppeal.pStatus is Visual) 2 else 1) to buffs.forVisual,
            ).mapIndexed { index, (ratio, buff) ->
                MemoryJudge.values().foldIndexed(appeals[index]) { i, acc, judge ->
                    val appeal = acc[i] + Appeal(floor(floor(baseAppeal * buff * judge) * memoryLevel).toInt() * ratio)
                    acc.apply { set(i, appeal) }
                }
            }
        }

        private const val IDOLS_COUNT = 5
    }
}
