package net.subroh0508.wingcalculator.usecase.simple

import net.subroh0508.wingcalculator.preset.model.Preset

interface FetchLatestModifiedPresetUseCase {
    suspend fun execute(): Preset?
}