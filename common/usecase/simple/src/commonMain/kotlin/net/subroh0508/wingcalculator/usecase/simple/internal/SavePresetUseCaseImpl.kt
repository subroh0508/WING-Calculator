package net.subroh0508.wingcalculator.usecase.simple.internal

import net.subroh0508.wingcalculator.appeal.model.Idol
import net.subroh0508.wingcalculator.preset.infra.repository.PresetRepository
import net.subroh0508.wingcalculator.producer.infra.repository.ProducerRepository
import net.subroh0508.wingcalculator.usecase.simple.SavePresetUseCase

internal class SavePresetUseCaseImpl(
    private val producerRepository: ProducerRepository,
    private val presetRepository: PresetRepository,
) : SavePresetUseCase {
    override suspend fun execute(
        id: Long,
        name: String,
        pIdol: Idol.Produce,
        sIdols: List<Idol.Support>,
        comment: String?,
    ) = presetRepository.save(
        id,
        producerRepository.fetchCurrent().id,
        name,
        pIdol,
        sIdols,
        comment,
    )

    override suspend fun execute(
        pIdol: Idol.Produce,
        sIdols: List<Idol.Support>,
        comment: String?
    ) = presetRepository.fetchBlankName(producerRepository.fetchCurrent().id)?.let { (id, _) ->
        execute(id, "", pIdol, sIdols, comment)
    } ?: presetRepository.save(producerRepository.fetchCurrent().id, "", pIdol, sIdols, comment)
}