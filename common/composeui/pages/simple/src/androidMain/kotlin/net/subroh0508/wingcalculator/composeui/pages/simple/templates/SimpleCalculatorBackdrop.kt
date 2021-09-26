@file:Suppress("FunctionName")
@file:JvmName("AndroidSimpleCalculatorBackdrop")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import net.subroh0508.wingcalculator.composeui.components.atoms.backdrop.FrontLayerHeader
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.ResponsiveDrawerState
import net.subroh0508.wingcalculator.composeui.pages.simple.templates.backdrop.BackLayerContent
import net.subroh0508.wingcalculator.composeui.pages.simple.templates.backdrop.FrontLayerContent

@Composable
actual fun SimpleCalculatorBackdrop(drawerState: ResponsiveDrawerState) {
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
                FrontLayerContent(
                    headerContent = {
                        AndroidBackdropFrontLayerHeader(
                            coroutineScope,
                            backdropScaffoldState,
                        )
                    },
                    onHeightChange = { frontLayerHeightState = it },
                )
            },
            scaffoldState = backdropScaffoldState,
            peekHeight = peekHeight,
        )
    }
}

@Composable
private fun ColumnScope.AndroidBackdropFrontLayerHeader(
    coroutineScope: CoroutineScope,
    backdropScaffoldState: BackdropScaffoldState,
) {
    FrontLayerHeader(
        "計算結果",
        BackdropScaffoldDefaults.HeaderHeight,
        backdropScaffoldState.isConcealed,
        onClickIcon = {
            coroutineScope.launch {
                if (backdropScaffoldState.isConcealed)
                    backdropScaffoldState.reveal()
                else
                    backdropScaffoldState.conceal()
            }
        },
    )
    Divider(Modifier.padding(horizontal = 8.dp))
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
