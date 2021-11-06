package net.subroh0508.wingcalculator.preference.infra.repository

import net.subroh0508.wingcalculator.preference.model.AppPreference

interface AppPreferenceRepository {
    fun get(producerId: Long): AppPreference

    suspend fun fetch(producerId: Long): AppPreference
    suspend fun update(producerId: Long, preference: AppPreference): AppPreference
}
