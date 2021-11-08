package net.subroh0508.wingcalculator.appeal.model.buffform

data class AppealJudge(
    val factor: Factor,
    internal val excellent: Boolean = false,
) {
    enum class Factor(internal val ratio: Double) {
        BAD(0.5), NORMAL(1.0), GOOD(1.1), PERFECT(1.5);

        override fun toString() = name.lowercase()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
    }
}

internal operator fun Double.times(judge: AppealJudge) = this * judge.factor.ratio * if (judge.excellent) 2.0 else 1.0
internal operator fun Double.times(factor: AppealJudge.Factor) = this * factor.ratio
