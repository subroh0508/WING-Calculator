package net.subroh0508.wingcalculator.usecase.simple.internal

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.subroh0508.wingcalculator.preset.infra.repository.PresetRepository
import net.subroh0508.wingcalculator.producer.infra.repository.ProducerRepository
import net.subroh0508.wingcalculator.usecase.simple.SearchPresetUseCase

internal class SearchPresetUseCaseImpl(
    private val producerRepository: ProducerRepository,
    private val presetRepository: PresetRepository,
) : SearchPresetUseCase {
    override suspend fun execute(name: String?) = withContext(Dispatchers.Default) {
        presetRepository.search(name, producerRepository.fetchCurrent().id)
    }
}
