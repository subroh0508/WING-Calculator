package net.subroh0508.wingcalculator.usecase.preference

import net.subroh0508.wingcalculator.preference.model.AppPreference

interface UpdateAppPreferenceUseCase {
    suspend fun execute(preference: AppPreference): AppPreference
}
