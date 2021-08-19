package net.subroh0508.wingcalculator.desktop

import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorPage
import androidx.compose.ui.window.application
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowSize
import androidx.compose.ui.window.WindowState

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "WING Calculator",
        state = WindowState(size = WindowSize(320.dp, 800.dp)),
    ) {
        SimpleCalculatorPage()
    }
}