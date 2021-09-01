package net.subroh0508.wingcalculator.database

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.flow.first
import kotlinx.datetime.Clock

class ProducerDatabase(
    private val queries: ProducerQueries,
) {
    suspend fun getCurrent(): Producer {
        val anonymous = queries.selectAnonymous().asFlow().mapToOneOrNull().first()

        if (anonymous != null) {
            return anonymous
        }

        queries.addAnonymous(Clock.System.now().toEpochMilliseconds())
        return queries.selectAnonymous().asFlow().mapToOne().first()
    }
}
