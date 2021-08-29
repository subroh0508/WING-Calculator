package net.subroh0508.wingcalculator.core.pages

import net.subroh0508.wingcalculator.preset.infra.di.PresetRepositories
import net.subroh0508.wingcalculator.producer.infra.di.ProducerRepositories

val SimpleCalculatorModule get() =
        PresetRepositories.Module + ProducerRepositories.Module
