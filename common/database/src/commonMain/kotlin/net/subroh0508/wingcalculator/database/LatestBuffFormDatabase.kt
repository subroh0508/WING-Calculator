package net.subroh0508.wingcalculator.database

class LatestBuffFormDatabase(
    private val queries: LatestBuffFormQueries,
) {
    fun get() = queries.select().executeAsOne()

    fun update(
        week: Long?,
        season: String,
        vocalBuff: String,
        danceBuff: String,
        visualBuff: String,
        appealRatio: Double,
        appealJudge: String,
        interestRatio: String,
        memoryLevel: String,
    ) = queries.update(
        week,
        season,
        vocalBuff,
        danceBuff,
        visualBuff,
        appealRatio,
        appealJudge,
        interestRatio,
        memoryLevel,
    )
}
