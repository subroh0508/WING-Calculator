@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.SimpleCalculateResult

@Composable
fun SimpleCalculatorFrontLayerContent(
    headerHeight: Dp,
    backdropScaffoldState: BackdropScaffoldState,
) {
    val verticalScrollState = rememberScrollState(0)

    SimpleCalculatorBoxWithConstraints { constraints ->
        Column {
            FrontLayerHeader(headerHeight, backdropScaffoldState, constraints)
            Divider(constraints.padding(horizontal = 8.dp))
            Box(modifier = constraints.verticalScroll(verticalScrollState)) {
                SimpleCalculateResult()
            }
        }
    }
}

@Composable
private fun FrontLayerHeader(
    headerHeight: Dp,
    backdropScaffoldState: BackdropScaffoldState,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier = modifier.height(headerHeight)
            .padding(start = 16.dp),
    ) {
        Text(
            "計算結果",
            modifier = Modifier.weight(1F)
                .padding(vertical = 8.dp)
                .align(Alignment.CenterVertically),
        )
        IconButton(
            onClick = {
                coroutineScope.launch {
                    if (backdropScaffoldState.isConcealed)
                        backdropScaffoldState.reveal()
                    else
                        backdropScaffoldState.conceal()
                }
            }
        ) {
            val (icon, description) =
                if (backdropScaffoldState.isConcealed)
                    Icons.Default.KeyboardArrowUp to "reveal"
                else
                    Icons.Default.KeyboardArrowDown to "conceal"

            Icon(icon, contentDescription = description)
        }
    }
}
