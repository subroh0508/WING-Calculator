package net.subroh0508.wingcalculator.web

import androidx.compose.runtime.*
import net.subroh0508.wingcalculator.appeal.model.getPlatformName
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Text

@Composable
fun App() {
    var text by remember { mutableStateOf("Hello, World") }

    Button(attrs = {
        onClick {
            text = "Hello, ${getPlatformName()}"
        }
    }) {
        Text(text)
    }
}
