@file:Suppress("FunctionName")
@file:JvmName("AndroidSimpleCalculatorBackdrop")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.CollapsingTopAppBarContainer

@Composable
actual fun SimpleCalculatorBackdrop() {
    val backdropScaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed)
    val coroutineScope = rememberCoroutineScope()

    var frontLayerHeightState by remember { mutableStateOf(BackdropScaffoldDefaults.HeaderHeight) }

    BoxWithConstraints {
        val peekHeight = calculatePeekHeight(maxHeight, frontLayerHeightState)

        BackdropScaffold(
            appBar = {},
            backLayerContent = {
                SimpleCalculatorBackLayerContent(
                    if (backdropScaffoldState.isConcealed)
                        frontLayerHeightState
                    else
                        BackdropScaffoldDefaults.HeaderHeight
                )
            },
            backLayerBackgroundColor = MaterialTheme.colors.background,
            frontLayerContent = {
                SimpleCalculatorFrontLayerContent(
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
