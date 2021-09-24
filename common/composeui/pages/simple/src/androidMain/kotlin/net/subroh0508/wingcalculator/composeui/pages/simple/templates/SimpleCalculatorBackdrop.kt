@file:Suppress("FunctionName")
@file:JvmName("AndroidSimpleCalculatorBackdrop")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.Dp
import kotlinx.coroutines.launch
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.ResponsibleDrawerState
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.ShrinkableDrawerState
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.CalculatorResultLayout

@Composable
actual fun SimpleCalculatorBackdrop(drawerState: ResponsibleDrawerState<LayoutConstraints>) {
    val backdropScaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed)
    val coroutineScope = rememberCoroutineScope()

    var frontLayerHeightState by remember { mutableStateOf(BackdropScaffoldDefaults.HeaderHeight) }

    BoxWithConstraints {
        val peekHeight = calculatePeekHeight(maxHeight, frontLayerHeightState)

        BackdropScaffold(
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
                    backdropScaffoldState.isConcealed,
                ) {
                    coroutineScope.launch {
                        if (backdropScaffoldState.isConcealed)
                            backdropScaffoldState.reveal()
                        else
                            backdropScaffoldState.conceal()
                    }
                }
            },
            scaffoldState = backdropScaffoldState,
            peekHeight = peekHeight,
        )
    }
}

private fun calculatePeekHeight(maxHeight: Dp, frontLayerHeight: Dp): Dp {
    if (frontLayerHeight == BackdropScaffoldDefaults.HeaderHeight) {
        return BackdropScaffoldDefaults.PeekHeight
    }

    return (maxHeight - frontLayerHeight).let { calcPeekHeight ->
        if (BackdropScaffoldDefaults.PeekHeight < calcPeekHeight)
            calcPeekHeight
        else
            BackdropScaffoldDefaults.PeekHeight
    }
}
