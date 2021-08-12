package net.subroh0508.wingcalculator.data

data class AppealJudge(
    internal val factor: Factor,
    internal val excellent: Boolean = false,
) {
    enum class Factor(internal val ratio: Double) {
        BAD(0.5), NORMAL(1.0), GOOD(1.1), PERFECT(1.5)
    }
}

internal operator fun Double.times(judge: AppealJudge) = this * judge.factor.ratio * if (judge.excellent) 2.0 else 1.0
