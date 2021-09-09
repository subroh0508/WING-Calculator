package net.subroh0508.wingcalculator.usecase.simple.internal

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.subroh0508.wingcalculator.preset.infra.repository.PresetRepository
import net.subroh0508.wingcalculator.producer.infra.repository.ProducerRepository
import net.subroh0508.wingcalculator.usecase.simple.DeletePresetUseCase

internal class DeletePresetUseCaseImpl(
    private val producerRepository: ProducerRepository,
    private val presetRepository: PresetRepository,
) : DeletePresetUseCase {
    override suspend fun execute(id: Long) = withContext(Dispatchers.Default) {
        presetRepository.delete(id, producerRepository.fetchCurrent().id)
    }
}