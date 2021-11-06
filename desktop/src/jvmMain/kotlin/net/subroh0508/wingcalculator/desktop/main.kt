package net.subroh0508.wingcalculator.desktop

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowSize
import androidx.compose.ui.window.WindowState
import net.subroh0508.wingcalculator.composeui.appframe.AppFrame
import net.subroh0508.wingcalculator.core.DesktopAppModule
import net.subroh0508.wingcalculator.core.provideAppPreference
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(DesktopAppModule)
    }

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "WING Calculator",
            state = WindowState(width = 576.dp, height = 800.dp),
        ) {
            AppFrame(provideAppPreference())
        }
    }
}