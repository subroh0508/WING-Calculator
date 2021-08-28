package net.subroh0508.wingcalculator.database.di

import net.subroh0508.wingcalculator.database.WingCalculatorDatabase
import org.koin.dsl.module

object WingCalculatorDatabases {
    val Module get() = module {
        single { WingCalculatorDatabase(get()) }
    }
}