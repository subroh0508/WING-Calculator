package net.subroh0508.wingcalculator.usecase.simple.di

import net.subroh0508.wingcalculator.appeal.infra.di.BuffFormRepositories
import net.subroh0508.wingcalculator.preset.infra.di.PresetRepositories
import net.subroh0508.wingcalculator.usecase.simple.*
import net.subroh0508.wingcalculator.usecase.simple.internal.*
import net.subroh0508.wingcalculator.usecase.simple.internal.CreatePresetUseCaseImpl
import net.subroh0508.wingcalculator.usecase.simple.internal.DeletePresetUseCaseImpl
import net.subroh0508.wingcalculator.usecase.simple.internal.SavePresetUseCaseImpl
import net.subroh0508.wingcalculator.usecase.simple.internal.SearchPresetUseCaseImpl
import org.koin.dsl.module

val SimpleCalculatorDomainModule get() = PresetRepositories.Module + BuffFormRepositories.Module + module {
    single<FetchLatestModifiedPresetUseCase> { FetchLatestModifiedPresetUseCaseImpl(get(), get(), get()) }
    single<SearchPresetUseCase> { SearchPresetUseCaseImpl(get(), get()) }
    single<CreatePresetUseCase> { CreatePresetUseCaseImpl(get(), get()) }
    single<SavePresetUseCase> { SavePresetUseCaseImpl(get(), get(), get()) }
    single<DeletePresetUseCase> { DeletePresetUseCaseImpl(get(), get()) }
}
