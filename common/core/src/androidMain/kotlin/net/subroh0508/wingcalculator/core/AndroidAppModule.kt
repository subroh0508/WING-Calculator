@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.core

import android.content.Context
import net.subroh0508.wingcalculator.database.di.SqlDriver
import net.subroh0508.wingcalculator.database.di.WingCalculatorDatabases

fun AndroidAppModule(context: Context) =
    SqlDriver.factoryModule(context) + SqlDriver.Module +
        WingCalculatorDatabases.Module
