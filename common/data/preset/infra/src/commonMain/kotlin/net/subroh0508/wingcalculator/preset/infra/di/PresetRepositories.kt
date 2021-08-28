package net.subroh0508.wingcalculator.preset.infra.di

import net.subroh0508.wingcalculator.preset.infra.repository.PresetRepository
import net.subroh0508.wingcalculator.preset.infra.repository.PresetRepositoryImpl
import org.koin.dsl.module

object PresetRepositories {
    val Module get() = module {
        single<PresetRepository> { PresetRepositoryImpl(get()) }
    }
}