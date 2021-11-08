package net.subroh0508.wingcalculator.appeal.infra.di

import net.subroh0508.wingcalculator.appeal.infra.repository.BuffFormRepository
import net.subroh0508.wingcalculator.appeal.infra.repository.BuffFormRepositoryImpl
import net.subroh0508.wingcalculator.database.di.LatestBuffDatabases
import org.koin.dsl.module

object BuffFormRepositories {
    val Module get() = LatestBuffDatabases.Module + module {
        single<BuffFormRepository> { BuffFormRepositoryImpl(get()) }
    }
}
