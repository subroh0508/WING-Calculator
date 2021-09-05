package net.subroh0508.wingcalculator.android

import android.app.Application
import net.subroh0508.wingcalculator.core.AndroidAppModule
import org.koin.core.context.startKoin

class WingCalculatorApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(AndroidAppModule(this@WingCalculatorApplication))
        }
    }
}