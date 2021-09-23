package net.subroh0508.wingcalculator.usecase.simple

import net.subroh0508.wingcalculator.appeal.model.Idol
import net.subroh0508.wingcalculator.preset.model.Preset

interface SavePresetUseCase {
    suspend fun execute(
        id: Long,
        name: String,
        pIdol: Idol.Produce,
        sIdols: List<Idol.Support>,
        comment: String?,
    ): Preset

    suspend fun execute(
        pIdol: Idol.Produce,
        sIdols: List<Idol.Support>,
        comment: String?,
    ): Preset
}