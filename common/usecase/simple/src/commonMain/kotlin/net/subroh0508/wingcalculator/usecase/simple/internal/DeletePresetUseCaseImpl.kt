package net.subroh0508.wingcalculator.usecase.simple.internal

import net.subroh0508.wingcalculator.preset.infra.repository.PresetRepository
import net.subroh0508.wingcalculator.producer.infra.repository.ProducerRepository
import net.subroh0508.wingcalculator.usecase.simple.DeletePresetUseCase

internal class DeletePresetUseCaseImpl(
    private val producerRepository: ProducerRepository,
    private val presetRepository: PresetRepository,
) : DeletePresetUseCase {
    override suspend fun execute(id: Long) = presetRepository.delete(id, producerRepository.fetchCurrent().id)
}