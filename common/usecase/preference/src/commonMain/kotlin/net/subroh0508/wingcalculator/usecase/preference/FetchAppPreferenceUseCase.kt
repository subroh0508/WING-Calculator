package net.subroh0508.wingcalculator.usecase.preference

import net.subroh0508.wingcalculator.preference.model.AppPreference

interface FetchAppPreferenceUseCase {
    suspend fun execute(): AppPreference
}
