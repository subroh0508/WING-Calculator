package net.subroh0508.wingcalculator.usecase.simple.internal

import net.subroh0508.wingcalculator.preset.infra.repository.PresetRepository
import net.subroh0508.wingcalculator.producer.infra.repository.ProducerRepository
import net.subroh0508.wingcalculator.usecase.simple.FetchLatestModifiedPresetUseCase

internal class FetchLatestModifiedPresetUseCaseImpl(
    private val producerRepository: ProducerRepository,
    private val presetRepository: PresetRepository,
) : FetchLatestModifiedPresetUseCase {
    override suspend fun execute() = presetRepository.fetchLatestModified(producerRepository.fetchCurrent().id)
}