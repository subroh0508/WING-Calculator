package net.subroh0508.wingcalculator.usecase.simple

import net.subroh0508.wingcalculator.preset.model.Preset

interface SearchPresetUseCase {
    suspend fun execute(name: String): List<Preset>
}
