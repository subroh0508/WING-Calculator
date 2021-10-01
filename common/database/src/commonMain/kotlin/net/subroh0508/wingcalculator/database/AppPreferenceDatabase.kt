package net.subroh0508.wingcalculator.database

import kotlinx.datetime.Clock

class AppPreferenceDatabase(
        private val queries: AppPreferenceQueries,
) {
    fun get(producerId: Long) = queries.select(producerId).executeAsOneOrNull()

    fun add(
            producerId: Long,
            theme: Theme = Theme.SYSTEM,
            tableType: TableType = TableType.APPEAL,
    ): AppPreference {
        queries.add(theme, tableType, producerId, Clock.System.now().toEpochMilliseconds())

        return queries.select(producerId).executeAsOne()
    }

    fun update(
            producerId: Long,
            theme: Theme = Theme.SYSTEM,
            tableType: TableType = TableType.APPEAL,
    ): AppPreference {
        val preference = get(producerId) ?: return add(producerId, theme, tableType)

        queries.update(theme, tableType, Clock.System.now().toEpochMilliseconds(), preference.id)

        return queries.select(producerId).executeAsOne()
    }
}
