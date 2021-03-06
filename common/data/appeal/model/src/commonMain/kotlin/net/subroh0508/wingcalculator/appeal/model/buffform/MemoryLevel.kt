package net.subroh0508.wingcalculator.appeal.model.buffform

enum class MemoryLevel(internal val ratio: Double) {
    ONE(0.8), TWO(1.0), THREE(1.2), FOUR(1.4), FIVE(2.0);

    override fun toString() = "Lv.${ordinal + 1}"
}

internal operator fun Double.times(ratio: MemoryLevel) = this * ratio.ratio
