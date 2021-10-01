package net.subroh0508.wingcalculator.usecase.preference.internal

import net.subroh0508.wingcalculator.preference.infra.repository.AppPreferenceRepository
import net.subroh0508.wingcalculator.producer.infra.repository.ProducerRepository
import net.subroh0508.wingcalculator.usecase.preference.FetchAppPreferenceUseCase

internal class FetchAppPreferenceUseCaseImpl(
    private val appPreferenceRepository: AppPreferenceRepository,
    private val producerRepository: ProducerRepository,
) : FetchAppPreferenceUseCase {
    override suspend fun execute() = appPreferenceRepository.fetch(producerRepository.fetchCurrent().id)
}
