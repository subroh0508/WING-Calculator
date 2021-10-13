package net.subroh0508.wingcalculator.usecase.preference.di

import net.subroh0508.wingcalculator.preference.infra.di.AppPreferenceRepositories
import net.subroh0508.wingcalculator.producer.infra.di.ProducerRepositories
import net.subroh0508.wingcalculator.usecase.preference.FetchAppPreferenceUseCase
import net.subroh0508.wingcalculator.usecase.preference.UpdateAppPreferenceUseCase
import net.subroh0508.wingcalculator.usecase.preference.internal.FetchAppPreferenceUseCaseImpl
import net.subroh0508.wingcalculator.usecase.preference.internal.UpdateAppPreferenceUseCaseImpl
import org.koin.dsl.module

val AppPreferenceDomainModule get() = ProducerRepositories.Module + AppPreferenceRepositories.Module + module {
    single<FetchAppPreferenceUseCase> { FetchAppPreferenceUseCaseImpl(get(), get()) }
    single<UpdateAppPreferenceUseCase> { UpdateAppPreferenceUseCaseImpl(get(), get()) }
}
