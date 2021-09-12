package net.subroh0508.wingcalculator.database

import kotlinx.datetime.Clock

class ProducerDatabase(
    private val queries: ProducerQueries,
) {
    fun getCurrent(): Producer {
        val anonymous = queries.selectAnonymous().executeAsOneOrNull()

        if (anonymous != null) {
            return anonymous
        }

        queries.addAnonymous(Clock.System.now().toEpochMilliseconds())
        return queries.selectAnonymous().executeAsOne()
    }
}
