package net.subroh0508.wingcalculator.appeal.model.buffform

data class Week(
    val season: Season,
    val week: Int = 1,
) {
    internal operator fun times(factor: Double) = when (season) {
        Season.SEMI_FINAL -> 32
        Season.FINAL -> 33
        else -> (season.ordinal * 8) + (week - 1)
    } * factor

    enum class Season {
        ONE, TWO, THREE, FOUR, SEMI_FINAL, FINAL;

        override fun toString() = when (this) {
            ONE -> "シーズン1"
            TWO -> "シーズン2"
            THREE -> "シーズン3"
            FOUR -> "シーズン4"
            SEMI_FINAL -> "準決勝"
            FINAL -> "決勝"
        }
    }
}