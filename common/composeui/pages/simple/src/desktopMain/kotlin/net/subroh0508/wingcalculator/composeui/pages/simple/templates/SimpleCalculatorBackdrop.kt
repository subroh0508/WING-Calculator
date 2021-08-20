@file:Suppress("FunctionName")
@file:JvmName("DesktopSimpleCalculatorBackdrop")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates

import androidx.compose.material.BackdropScaffoldDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import net.subroh0508.wingcalculator.composeui.components.atoms.StaticBackdrop

@Composable
actual fun SimpleCalculatorBackdrop() {
    var frontLayerHeightState by remember { mutableStateOf(BackdropScaffoldDefaults.HeaderHeight) }

    StaticBackdrop(
        appBar = {},
        backLayerContent = {
            SimpleCalculatorBackLayerContent(frontLayerHeightState)
        },
        backLayerBackgroundColor = MaterialTheme.colors.background,
        frontLayerContent = {
            SimpleCalculatorFrontLayerContent(
                BackdropScaffoldDefaults.HeaderHeight,
                onChangeHeight = { frontLayerHeightState = it },
            )
        },
    )
}