@file:Suppress("FunctionName")
@file:JvmName("DesktopSimpleCalculatorBackdrop")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates

import androidx.compose.material.BackdropScaffoldDefaults
import androidx.compose.material.DrawerState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import net.subroh0508.wingcalculator.composeui.components.atoms.StaticBackdrop

@Composable
actual fun SimpleCalculatorBackdrop(drawerState: DrawerState) {
    var frontLayerHeightState by remember { mutableStateOf(BackdropScaffoldDefaults.HeaderHeight) }

    val coroutineScope = rememberCoroutineScope()

    StaticBackdrop(
        appBar = {},
        backLayerContent = {
            SimpleCalculatorBackLayerContent(
                frontLayerHeightState,
            )  {
                coroutineScope.launch {
                    if (drawerState.isOpen)
                        drawerState.close()
                    else
                        drawerState.open()
                }
            }
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