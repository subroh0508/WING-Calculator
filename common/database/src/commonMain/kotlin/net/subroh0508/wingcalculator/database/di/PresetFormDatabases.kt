package net.subroh0508.wingcalculator.database.di

import net.subroh0508.wingcalculator.database.PresetDatabase
import net.subroh0508.wingcalculator.database.WingCalculatorDatabase
import org.koin.dsl.module

object PresetFormDatabases {
    val Module get() = module {
        single { PresetDatabase(get<WingCalculatorDatabase>().presetFormQueries) }
    }
}