package net.subroh0508.wingcalculator.database.factory

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import net.subroh0508.wingcalculator.database.WingCalculatorDatabase
import net.subroh0508.wingcalculator.database.di.DB_NAME

class SqlDriverFactory(private val context: Context) {
    fun create(): SqlDriver = AndroidSqliteDriver(WingCalculatorDatabase.Schema, context, DB_NAME)
}
