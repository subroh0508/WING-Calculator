package net.subroh0508.wingcalculator.appeal.model.buffform

import net.subroh0508.wingcalculator.appeal.model.internal.AppealTypeIndex

data class Buff(
    internal val ratio: List<Double> = listOf(0.0),
) {
    override fun toString() = ratio.joinToString(",") { (it * 100).toInt().toString() }

    internal fun sum() = ratio.sum()

    fun total() = "${(sum() * 100).toInt()}%"
}

data class Buffs(
    private val values: List<Buff> = listOf(Buff(), Buff(), Buff()),
) : List<Buff> by values {
    val forVocal get() = get(AppealTypeIndex.Vo)
    val forDance get() = get(AppealTypeIndex.Da)
    val forVisual get() = get(AppealTypeIndex.Vi)

    constructor(
        vocal: List<Double>,
        dance: List<Double>,
        visual: List<Double>,
    ) : this(listOf(Buff(vocal), Buff(dance), Buff(visual)))

    constructor(index: Int, buff: List<Double>, buffs: Buffs) : this(
        buffs.mapIndexed { i, b -> if (index == i) Buff(buff) else b },
    )

    private operator fun get(type: AppealTypeIndex) = values[type.ordinal]
}

internal operator fun Double.times(buff: Buff) = (1.0 + buff.sum()) * this
