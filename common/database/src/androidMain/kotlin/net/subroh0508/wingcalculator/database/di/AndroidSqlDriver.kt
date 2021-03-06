package net.subroh0508.wingcalculator.database.di

import android.content.Context
import net.subroh0508.wingcalculator.database.WingCalculatorDatabase
import net.subroh0508.wingcalculator.database.factory.SqlDriverFactory
import org.koin.dsl.module

actual object SqlDriver {
    actual val Module get() = module {
        single { get<SqlDriverFactory>().create() }
    }

    fun factoryModule(context: Context) = module {
        single { SqlDriverFactory(context) }
    }
}