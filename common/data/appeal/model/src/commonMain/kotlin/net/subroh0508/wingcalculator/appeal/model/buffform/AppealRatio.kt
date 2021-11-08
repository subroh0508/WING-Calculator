package net.subroh0508.wingcalculator.appeal.model.buffform

import net.subroh0508.wingcalculator.utilities.extensions.toFixed

data class AppealRatio(
    val ratio: Double,
) {
    override fun toString() = "${ratio.toFixed(1)}倍"
}

internal operator fun Double.times(ratio: AppealRatio) = this * ratio.ratio
