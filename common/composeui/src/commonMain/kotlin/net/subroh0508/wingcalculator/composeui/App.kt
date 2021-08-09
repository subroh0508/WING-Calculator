package net.subroh0508.wingcalculator.composeui

import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*

@Composable
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        Button(onClick = {
            text = "Hello, ${net.subroh0508.wingcalculator.composeui.getPlatformName()}"
        }) {
            Text(text)
        }
    }
}
