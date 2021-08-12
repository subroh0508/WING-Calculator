package net.subroh0508.wingcalculator.data

data class InterestRatio(
    internal val ratio: Double,
)

internal operator fun Double.times(ratio: InterestRatio) = this * ratio.ratio
