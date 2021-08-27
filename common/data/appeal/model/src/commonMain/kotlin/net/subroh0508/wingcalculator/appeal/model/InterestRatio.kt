package net.subroh0508.wingcalculator.appeal.model

import net.subroh0508.wingcalculator.utilities.extensions.toFixed

data class InterestRatio(
    internal val ratio: List<Double>,
) {
    override fun toString() = ratio.joinToString(",") { it.toFixed(2) }

    internal fun fold() = if (ratio.isEmpty()) 1.0 else ratio.fold(1.0) { acc, r -> acc * r }

    fun total() = "${fold().toFixed(2)}倍"
}

internal operator fun Double.times(ratio: InterestRatio) = this * ratio.fold()
