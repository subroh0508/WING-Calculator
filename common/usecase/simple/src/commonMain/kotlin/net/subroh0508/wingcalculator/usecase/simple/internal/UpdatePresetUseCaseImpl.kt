package net.subroh0508.wingcalculator.usecase.simple.internal

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.subroh0508.wingcalculator.appeal.model.Idol
import net.subroh0508.wingcalculator.preset.infra.repository.PresetRepository
import net.subroh0508.wingcalculator.producer.infra.repository.ProducerRepository
import net.subroh0508.wingcalculator.usecase.simple.UpdatePresetUseCase

internal class UpdatePresetUseCaseImpl(
    private val producerRepository: ProducerRepository,
    private val presetRepository: PresetRepository,
) : UpdatePresetUseCase {
    override suspend fun execute(
        id: Long,
        name: String,
        pIdol: Idol.Produce,
        sIdols: List<Idol.Support>,
        comment: String?,
    ) = withContext(Dispatchers.Default) {
        presetRepository.save(
            id,
            producerRepository.fetchCurrent().id,
            name,
            pIdol,
            sIdols,
            comment,
        )
    }
}

