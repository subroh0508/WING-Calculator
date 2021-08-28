package net.subroh0508.wingcalculator.database.factory

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import net.subroh0508.wingcalculator.database.WingCalculatorDatabase

class SqlDriverFactory(private val context: Context) {
    fun create(): SqlDriver = AndroidSqliteDriver(WingCalculatorDatabase.Schema, context, DB_NAME)

    companion object {
        private const val DB_NAME = "WingCalculatorDatabase.db"
    }
}
