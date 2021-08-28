package net.subroh0508.wingcalculator.producer.infra.repository.db

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.flow.first
import net.subroh0508.wingcalculator.database.Producer
import net.subroh0508.wingcalculator.database.ProducerQueries

class ProducerDatabase(
    private val queries: ProducerQueries,
) {
    suspend fun getCurrent(): Producer {
        val anonymous = queries.selectAnonymous().asFlow().mapToOneOrNull().first()

        if (anonymous != null) {
            return anonymous
        }

        queries.addAnonymous()
        return queries.selectAnonymous().asFlow().mapToOne().first()
    }
}