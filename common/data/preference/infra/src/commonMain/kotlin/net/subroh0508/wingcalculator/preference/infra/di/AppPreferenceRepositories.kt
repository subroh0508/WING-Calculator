package net.subroh0508.wingcalculator.preference.infra.di

import net.subroh0508.wingcalculator.database.di.AppPreferenceDatabases
import net.subroh0508.wingcalculator.preference.infra.repository.AppPreferenceRepository
import net.subroh0508.wingcalculator.preference.infra.repository.AppPreferenceRepositoryImpl
import org.koin.dsl.module

object AppPreferenceRepositories {
    val Module get() = AppPreferenceDatabases.Module + module {
        single<AppPreferenceRepository> { AppPreferenceRepositoryImpl(get()) }
    }
}
