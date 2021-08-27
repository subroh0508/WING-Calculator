package net.subroh0508.wingcalculator.preset.infra.repository.db

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import net.subroh0508.wingcalculator.database.PresetFormQueries

internal class PresetDatabase(
    private val queries: PresetFormQueries,
) {
    suspend fun get(id: Long, producerId: Long) = withContext(Dispatchers.Default) {
        queries.select(id, producerId).asFlow().mapToOne().first()
    }

    suspend fun search(producerId: Long, name: String) = withContext(Dispatchers.Default) {
        queries.selectByName(producerId, name, DEFAULT_LIMIT).asFlow().mapToList().first()
    }

    suspend fun create(
        producerId: Long,
        name: String,
        pIdolStatus: List<Long>,
        sIdolStatuses: List<List<Long>>,
        comment: String?,
    ) = withContext(Dispatchers.Default) {
        queries.add(
            name,
            pIdolStatus[0], pIdolStatus[1], pIdolStatus[2], pIdolStatus[3],
            sIdolStatuses[0][0], sIdolStatuses[0][1], sIdolStatuses[0][2],
            sIdolStatuses[1][0], sIdolStatuses[1][1], sIdolStatuses[1][2],
            sIdolStatuses[2][0], sIdolStatuses[2][1], sIdolStatuses[2][2],
            sIdolStatuses[3][0], sIdolStatuses[3][1], sIdolStatuses[3][2],
            comment,
            producerId,
            Clock.System.now().toEpochMilliseconds(),
        )

        queries.selectOfLatestCreatedAt(producerId).asFlow().mapToOne().first()
    }

    suspend fun update(
        id: Long,
        producerId: Long,
        name: String,
        pIdolStatus: List<Long>,
        sIdolStatuses: List<List<Long>>,
        comment: String?,
    ) = withContext(Dispatchers.Default) {
        queries.update(
            name,
            pIdolStatus[0], pIdolStatus[1], pIdolStatus[2], pIdolStatus[3],
            sIdolStatuses[0][0], sIdolStatuses[0][1], sIdolStatuses[0][2],
            sIdolStatuses[1][0], sIdolStatuses[1][1], sIdolStatuses[1][2],
            sIdolStatuses[2][0], sIdolStatuses[2][1], sIdolStatuses[2][2],
            sIdolStatuses[3][0], sIdolStatuses[3][1], sIdolStatuses[3][2],
            comment,
            Clock.System.now().toEpochMilliseconds(),
            producerId,
            id,
        )

        get(id, producerId)
    }

    companion object {
        private const val DEFAULT_LIMIT = 30L
    }
}