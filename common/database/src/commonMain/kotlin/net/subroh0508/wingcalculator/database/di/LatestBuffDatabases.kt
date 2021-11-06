package net.subroh0508.wingcalculator.database.di

import net.subroh0508.wingcalculator.database.LatestBuffFormDatabase
import net.subroh0508.wingcalculator.database.WingCalculatorDatabase
import org.koin.dsl.module

object LatestBuffDatabases {
    val Module get() = module {
        single { LatestBuffFormDatabase(get<WingCalculatorDatabase>().latestBuffFormQueries) }
    }
}
