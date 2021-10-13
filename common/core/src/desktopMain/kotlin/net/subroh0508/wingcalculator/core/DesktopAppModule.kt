package net.subroh0508.wingcalculator.core

import net.subroh0508.wingcalculator.database.di.SqlDriver
import net.subroh0508.wingcalculator.database.di.WingCalculatorDatabases
import net.subroh0508.wingcalculator.producer.infra.di.ProducerRepositories
import net.subroh0508.wingcalculator.usecase.preference.di.AppPreferenceDomainModule

val DesktopAppModule get() =
    SqlDriver.Module + WingCalculatorDatabases.Module + AppPreferenceDomainModule
