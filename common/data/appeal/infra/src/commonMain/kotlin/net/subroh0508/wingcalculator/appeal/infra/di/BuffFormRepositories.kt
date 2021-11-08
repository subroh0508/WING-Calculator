package net.subroh0508.wingcalculator.appeal.infra.di

import net.subroh0508.wingcalculator.appeal.infra.repository.BuffFormRepository
import net.subroh0508.wingcalculator.appeal.infra.repository.BuffFormRepositoryImpl
import org.koin.dsl.module

object BuffFormRepositories {
    val Module get() = module {
        single<BuffFormRepository> { BuffFormRepositoryImpl(get()) }
    }
}
