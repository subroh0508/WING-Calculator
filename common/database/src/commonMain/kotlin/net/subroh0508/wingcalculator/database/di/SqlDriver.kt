package net.subroh0508.wingcalculator.database.di

import org.koin.core.module.Module

internal const val DB_NAME = "WingCalculatorDatabase.db"

expect object SqlDriver {
    val Module: Module
}