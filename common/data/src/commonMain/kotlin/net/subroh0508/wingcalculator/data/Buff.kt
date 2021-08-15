package net.subroh0508.wingcalculator.data

data class Buff(
    internal val ratio: Double,
) {
    override fun toString() = (ratio * 100).toInt().toString()
}

data class Buffs(
    val forVocal: Buff,
    val forDance: Buff,
    val forVisual: Buff,
)

internal operator fun Double.times(buff: Buff) = (1.0 + buff.ratio) * this
