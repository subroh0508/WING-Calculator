package net.subroh0508.wingcalculator.core

import net.subroh0508.wingcalculator.database.di.SqlDriver
import net.subroh0508.wingcalculator.database.di.WingCalculatorDatabases

val DesktopAppModule get() =
    SqlDriver.Module + WingCalculatorDatabases.Module
