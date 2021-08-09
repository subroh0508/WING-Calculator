package net.subroh0508.wingcalculator.desktop

import net.subroh0508.wingcalculator.composeui.App
import androidx.compose.ui.window.application
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "WING Calculator",
        state = WindowState(),
    ) {
        App()
    }
}