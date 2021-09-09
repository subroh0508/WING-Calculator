package net.subroh0508.wingcalculator.database

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import kotlinx.coroutines.flow.first
import kotlinx.datetime.Clock

class PresetDatabase(
    private val queries: PresetFormQueries,
) {
    suspend fun get(id: Long, producerId: Long) = queries.select(id, producerId).asFlow().mapToOne().first()

    suspend fun search(producerId: Long, name: String) = queries.selectByName(producerId, name, DEFAULT_LIMIT).asFlow().mapToList().first()

    suspend fun create(
        producerId: Long,
        name: String,
        pIdolStatus: List<Long>,
        sIdolStatuses: List<List<Long>>,
        comment: String?,
    ): PresetForm {
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

        return queries.selectOfLatestCreatedAt(producerId).asFlow().mapToOne().first()
    }

    suspend fun update(
        id: Long,
        producerId: Long,
        name: String,
        pIdolStatus: List<Long>,
        sIdolStatuses: List<List<Long>>,
        comment: String?,
    ): PresetForm {
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

        return get(id, producerId)
    }

    fun delete(id: Long, producerId: Long) = queries.delete(id, producerId)

    companion object {
        private const val DEFAULT_LIMIT = 30L
    }
}