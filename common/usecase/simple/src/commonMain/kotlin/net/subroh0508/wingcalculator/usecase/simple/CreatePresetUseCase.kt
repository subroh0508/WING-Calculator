package net.subroh0508.wingcalculator.usecase.simple

import net.subroh0508.wingcalculator.appeal.model.Idol
import net.subroh0508.wingcalculator.preset.model.Preset

interface CreatePresetUseCase {
    suspend fun execute(
        name: String,
        pIdol: Idol.Produce,
        sIdols: List<Idol.Support>,
        comment: String? = null,
    ): Preset
}
