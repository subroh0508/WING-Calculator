package net.subroh0508.wingcalculator.database.di

import net.subroh0508.wingcalculator.database.ProducerDatabase
import net.subroh0508.wingcalculator.database.WingCalculatorDatabase
import org.koin.dsl.module

object ProducerDatabases {
    val Module get() = module {
        single { ProducerDatabase(get<WingCalculatorDatabase>().producerQueries) }
    }
}