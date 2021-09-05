package net.subroh0508.wingcalculator.database.di

import net.subroh0508.wingcalculator.database.MigrationExecutor
import org.koin.core.module.Module
import org.koin.dsl.module

expect object WingCalculatorDatabases {
    val Module: Module
}