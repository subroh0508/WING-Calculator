package net.subroh0508.wingcalculator.usecase.preference.internal

import net.subroh0508.wingcalculator.preference.infra.repository.AppPreferenceRepository
import net.subroh0508.wingcalculator.preference.model.AppPreference
import net.subroh0508.wingcalculator.producer.infra.repository.ProducerRepository
import net.subroh0508.wingcalculator.usecase.preference.UpdateAppPreferenceUseCase

internal class UpdateAppPreferenceUseCaseImpl(
    private val appPreferenceRepository: AppPreferenceRepository,
    private val producerRepository: ProducerRepository,
) : UpdateAppPreferenceUseCase {
    override suspend fun execute(preference: AppPreference) = appPreferenceRepository.update(
            producerRepository.fetchCurrent().id,
            preference,
    )
}
