package net.subroh0508.wingcalculator.usecase.simple.internal

import net.subroh0508.wingcalculator.appeal.infra.repository.BuffFormRepository
import net.subroh0508.wingcalculator.appeal.model.BuffForm
import net.subroh0508.wingcalculator.appeal.model.Idol
import net.subroh0508.wingcalculator.preset.infra.repository.PresetRepository
import net.subroh0508.wingcalculator.preset.model.Preset
import net.subroh0508.wingcalculator.producer.infra.repository.ProducerRepository
import net.subroh0508.wingcalculator.usecase.simple.SavePresetUseCase

internal class SavePresetUseCaseImpl(
    private val producerRepository: ProducerRepository,
    private val presetRepository: PresetRepository,
    private val buffFormRepository: BuffFormRepository,
) : SavePresetUseCase {
    override suspend fun execute(
        id: Long,
        name: String,
        pIdol: Idol.Produce,
        sIdols: List<Idol.Support>,
        buffForm: BuffForm,
        comment: String?,
    ): Preset {
        val preset = presetRepository.save(
            id,
            producerRepository.fetchCurrent().id,
            name,
            pIdol,
            sIdols,
            comment,
        )
        buffFormRepository.save(buffForm)

        return preset
    }

    override suspend fun execute(
        pIdol: Idol.Produce,
        sIdols: List<Idol.Support>,
        buffForm: BuffForm,
        comment: String?
    ): Preset {
        val preset = presetRepository.fetchBlankName(producerRepository.fetchCurrent().id)?.let { (id, _) ->
            execute(id, "", pIdol, sIdols, buffForm, comment)
        } ?: presetRepository.save(producerRepository.fetchCurrent().id, "", pIdol, sIdols, comment)

        buffFormRepository.save(buffForm)

        return preset
    }
}