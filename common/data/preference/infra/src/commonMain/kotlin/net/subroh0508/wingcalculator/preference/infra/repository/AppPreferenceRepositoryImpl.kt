package net.subroh0508.wingcalculator.preference.infra.repository

import net.subroh0508.wingcalculator.database.AppPreferenceDatabase
import net.subroh0508.wingcalculator.database.DBTableType
import net.subroh0508.wingcalculator.database.DBTheme
import net.subroh0508.wingcalculator.database.AppPreference as DBAppPreference
import net.subroh0508.wingcalculator.preference.model.AppPreference

internal class AppPreferenceRepositoryImpl(
        private val database: AppPreferenceDatabase,
) : AppPreferenceRepository {
    override suspend fun fetch(producerId: Long) = (database.get(producerId) ?: database.add(producerId)).toValueObject()
    override suspend fun update(producerId: Long, preference: AppPreference) = database.update(
            producerId,
            DBTheme.valueOf(preference.theme.name),
            DBTableType.valueOf(preference.table.name),
    ).toValueObject()

    private fun DBAppPreference.toValueObject() = AppPreference(
            AppPreference.Theme.valueOf(theme.name),
            AppPreference.Table.valueOf(tableType.name),
    )
}
