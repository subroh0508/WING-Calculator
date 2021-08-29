package net.subroh0508.wingcalculator.usecase.simple.di

import net.subroh0508.wingcalculator.preset.infra.di.PresetRepositories
import net.subroh0508.wingcalculator.producer.infra.di.ProducerRepositories
import net.subroh0508.wingcalculator.usecase.simple.CreatePresetUseCase
import net.subroh0508.wingcalculator.usecase.simple.SearchPresetUseCase
import net.subroh0508.wingcalculator.usecase.simple.UpdatePresetUseCase
import net.subroh0508.wingcalculator.usecase.simple.internal.CreatePresetUseCaseImpl
import net.subroh0508.wingcalculator.usecase.simple.internal.SearchPresetUseCaseImpl
import net.subroh0508.wingcalculator.usecase.simple.internal.UpdatePresetUseCaseImpl
import org.koin.dsl.module

val SimpleCalculatorDomainModule get() = PresetRepositories.Module + ProducerRepositories.Module + module {
    single<SearchPresetUseCase> { SearchPresetUseCaseImpl(get(), get()) }
    single<CreatePresetUseCase> { CreatePresetUseCaseImpl(get(), get()) }
    single<UpdatePresetUseCase> { UpdatePresetUseCaseImpl(get(), get()) }
}
