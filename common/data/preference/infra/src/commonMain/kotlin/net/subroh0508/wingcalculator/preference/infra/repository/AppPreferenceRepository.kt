package net.subroh0508.wingcalculator.preference.infra.repository

import net.subroh0508.wingcalculator.preference.model.AppPreference

interface AppPreferenceRepository {
    suspend fun fetch(producerId: Long): AppPreference
    suspend fun update(producerId: Long, preference: AppPreference): AppPreference
}
