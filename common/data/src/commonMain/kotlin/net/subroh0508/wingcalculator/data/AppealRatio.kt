package net.subroh0508.wingcalculator.data

data class AppealRatio(
    internal val ratio: Double,
)



internal operator fun Double.times(ratio: AppealRatio) = this * ratio.ratio
