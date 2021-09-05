package net.subroh0508.wingcalculator.database.di

import net.subroh0508.wingcalculator.database.WingCalculatorDatabase
import org.koin.dsl.module

actual object WingCalculatorDatabases {
    actual val Module get() = module {
        single { WingCalculatorDatabase(get()) }
    }
}