package net.subroh0508.wingcalculator.data

data class Buff(
    internal val ratio: Double,
)

data class Buffs(
    val forVocal: Buff,
    val forDance: Buff,
    val forVisual: Buff,
)

internal operator fun Double.times(buff: Buff) = buff.ratio * this
