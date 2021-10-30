package net.subroh0508.wingcalculator.appeal.model

enum class MemoryJudge(internal val ratio: Double) {
    GOOD(1.5), BAD(0.5);

    override fun toString() = name.first().toString()
}

internal operator fun Double.times(judge: MemoryJudge) = this * judge.ratio
