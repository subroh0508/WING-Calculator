package net.subroh0508.wingcalculator.core

import net.subroh0508.wingcalculator.preference.infra.repository.AppPreferenceRepository
import net.subroh0508.wingcalculator.preference.model.AppPreference
import net.subroh0508.wingcalculator.producer.infra.repository.ProducerRepository
import org.koin.mp.KoinPlatformTools

fun provideAppPreference(): AppPreference {
    val koin = KoinPlatformTools.defaultContext().get()

    val producerRepository = koin.get<ProducerRepository>()
    val appPreferenceRepository = koin.get<AppPreferenceRepository>()

    return appPreferenceRepository.get(producerRepository.getCurrent().id)
}
