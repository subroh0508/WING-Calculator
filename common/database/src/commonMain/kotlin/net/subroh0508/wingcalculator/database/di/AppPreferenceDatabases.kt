package net.subroh0508.wingcalculator.database.di

import net.subroh0508.wingcalculator.database.AppPreferenceDatabase
import net.subroh0508.wingcalculator.database.WingCalculatorDatabase
import org.koin.dsl.module

object AppPreferenceDatabases {
    val Module get() = module {
        single { AppPreferenceDatabase(get<WingCalculatorDatabase>().appPreferenceQueries) }
    }
}
