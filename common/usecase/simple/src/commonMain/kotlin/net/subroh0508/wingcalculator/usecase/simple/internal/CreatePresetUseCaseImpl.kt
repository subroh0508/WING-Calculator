package net.subroh0508.wingcalculator.usecase.simple.internal

import net.subroh0508.wingcalculator.appeal.model.Idol
import net.subroh0508.wingcalculator.preset.infra.repository.PresetRepository
import net.subroh0508.wingcalculator.producer.infra.repository.ProducerRepository
import net.subroh0508.wingcalculator.usecase.simple.CreatePresetUseCase

internal class CreatePresetUseCaseImpl(
    private val producerRepository: ProducerRepository,
    private val presetRepository: PresetRepository,
) : CreatePresetUseCase {
    override suspend fun execute(
        name: String,
        pIdol: Idol.Produce,
        sIdols: List<Idol.Support>,
        comment: String?,
    ) = presetRepository.save(
        producerRepository.fetchCurrent().id,
        name,
        pIdol,
        sIdols,
        comment,
    )
}
