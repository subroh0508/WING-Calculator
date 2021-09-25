@file:Suppress("FunctionName")
@file:JvmName("DesktopSimpleCalculatorBackdrop")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BackdropScaffoldDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import net.subroh0508.wingcalculator.composeui.components.atoms.StaticBackdrop
import net.subroh0508.wingcalculator.composeui.components.atoms.backdrop.FrontLayerHeader
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.ResponsiveDrawerState
import net.subroh0508.wingcalculator.composeui.pages.simple.templates.backdrop.BackLayerContent
import net.subroh0508.wingcalculator.composeui.pages.simple.templates.backdrop.FrontLayerContent

@Composable
actual fun SimpleCalculatorBackdrop(drawerState: ResponsiveDrawerState) {
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
            FrontLayerContent(
                headerContent = { DesktopBackdropFrontLayerHeader(it) },
                onChangeHeight = { frontLayerHeightState = it },
            )
        },
    )
}

@Composable
private fun ColumnScope.DesktopBackdropFrontLayerHeader(
    modifier: Modifier = Modifier,
) {
    FrontLayerHeader("計算結果", BackdropScaffoldDefaults.HeaderHeight)
    Divider(modifier.padding(horizontal = 8.dp))
}