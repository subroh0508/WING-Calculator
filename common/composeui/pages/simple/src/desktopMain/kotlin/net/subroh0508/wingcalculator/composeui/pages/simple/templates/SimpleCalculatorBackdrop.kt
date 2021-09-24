@file:Suppress("FunctionName")
@file:JvmName("DesktopSimpleCalculatorBackdrop")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates

import androidx.compose.material.BackdropScaffoldDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import net.subroh0508.wingcalculator.composeui.components.atoms.StaticBackdrop
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.ResponsibleDrawerState
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.CalculatorResultLayout

@Composable
actual fun SimpleCalculatorBackdrop(drawerState: ResponsibleDrawerState) {
    var frontLayerHeightState by remember { mutableStateOf(BackdropScaffoldDefaults.HeaderHeight) }

    val coroutineScope = rememberCoroutineScope()

    StaticBackdrop(
        appBar = {},
        backLayerContent = {
            BackLayerContent(
                frontLayerHeightState,
            ) {
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
            CalculatorResultLayout(
                BackdropScaffoldDefaults.HeaderHeight,
                onChangeHeight = { frontLayerHeightState = it },
            )
        },
    )
}