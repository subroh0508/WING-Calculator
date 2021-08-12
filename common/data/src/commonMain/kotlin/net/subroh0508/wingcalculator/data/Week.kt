package net.subroh0508.wingcalculator.data

data class Week(
    private val season: Season,
    private val week: Int,
) {
    internal operator fun times(factor: Double) = when (season) {
        Season.SEMI_FINAL -> 32
        Season.FINAL -> 33
        else -> (season.ordinal + 1) * (week - 1)
    } * factor

    enum class Season {
        ONE, TWO, THREE, FOUR, SEMI_FINAL, FINAL
    }
}