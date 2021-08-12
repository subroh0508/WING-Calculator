package net.subroh0508.wingcalculator.data

data class Buff(
    internal val ratio: Double,
)

internal operator fun Double.times(buff: Buff) = buff.ratio * this
