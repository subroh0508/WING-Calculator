package net.subroh0508.wingcalculator.data

import net.subroh0508.wingcalculator.utilities.extensions.toFixed

data class InterestRatio(
    internal val ratio: List<Double>,
) {
    override fun toString() = ratio.joinToString(",") { it.toFixed(2) }
}

internal operator fun Double.times(ratio: InterestRatio) = this * (
        if (ratio.ratio.isEmpty())
            1.0
        else
            ratio.ratio.fold(1.0) { acc, r -> acc * r }
        )
