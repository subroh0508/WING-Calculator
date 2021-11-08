package net.subroh0508.wingcalculator.usecase.simple

import net.subroh0508.wingcalculator.appeal.model.BuffForm
import net.subroh0508.wingcalculator.preset.model.Preset

interface FetchLatestModifiedPresetUseCase {
    suspend fun execute(): Pair<Preset?, BuffForm>
}