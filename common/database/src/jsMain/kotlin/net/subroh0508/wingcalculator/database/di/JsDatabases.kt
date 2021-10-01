package net.subroh0508.wingcalculator.database.di

import com.squareup.sqldelight.EnumColumnAdapter
import net.subroh0508.wingcalculator.database.AppPreference
import net.subroh0508.wingcalculator.database.WingCalculatorDatabase
import org.koin.dsl.module

actual object WingCalculatorDatabases {
    actual val Module get() = module {
        single {
            WingCalculatorDatabase(
                    get(),
                    AppPreferenceAdapter = AppPreference.Adapter(
                            themeAdapter = EnumColumnAdapter(),
                            tableTypeAdapter = EnumColumnAdapter(),
                    ),
            )
        }
    }
}
