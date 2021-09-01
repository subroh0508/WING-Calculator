package net.subroh0508.wingcalculator.database.di

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import net.subroh0508.wingcalculator.database.WingCalculatorDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual object SqlDriver {
    actual val Module: Module get() = module {
        single<SqlDriver> {
            JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
                .also {
                    WingCalculatorDatabase.Schema.create(it)
                    WingCalculatorDatabase.Schema.migrate(it, 0, WingCalculatorDatabase.Schema.version)
                }
        }
    }
}
