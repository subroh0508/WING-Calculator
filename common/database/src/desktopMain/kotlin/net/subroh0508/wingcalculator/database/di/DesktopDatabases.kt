package net.subroh0508.wingcalculator.database.di

import com.squareup.sqldelight.db.SqlCursor
import com.squareup.sqldelight.db.SqlDriver
import net.subroh0508.wingcalculator.database.WingCalculatorDatabase
import org.koin.dsl.module

actual object WingCalculatorDatabases {
    actual val Module get() = module {
        single { MigrationExecutor(get()).init() }
    }
}

private class MigrationExecutor(private val driver: SqlDriver) {
    fun init(): WingCalculatorDatabase {
        val currentVersion = version
        val schemaVersion = WingCalculatorDatabase.Schema.version

        if (currentVersion == 0) {
            WingCalculatorDatabase.Schema.create(driver)
            WingCalculatorDatabase.Schema.migrate(driver, 0, schemaVersion)
            version = schemaVersion
        } else if (currentVersion < schemaVersion){
            WingCalculatorDatabase.Schema.migrate(driver, currentVersion, schemaVersion)
            version = schemaVersion
        }

        return WingCalculatorDatabase(driver)
    }

    private var version: Int
        get() {
            val sqlCursor = driver.executeQuery(null, "PRAGMA user_version;", 0, null)
                .apply(SqlCursor::next)
            return sqlCursor.getLong(0)?.toInt() ?: 0
        }
        private set(version) {
            driver.execute(null, "PRAGMA user_version = $version;", 0, null)
        }
}
