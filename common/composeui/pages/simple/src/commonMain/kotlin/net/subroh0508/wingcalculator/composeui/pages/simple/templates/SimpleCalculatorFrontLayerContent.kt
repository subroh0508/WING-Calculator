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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.SimpleCalculateResult

@Composable
fun SimpleCalculatorFrontLayerContent(
    headerHeight: Dp,
    onChangeHeight: (Dp) -> Unit,
    isConcealed: Boolean? = null,
    onClickIcon: () -> Unit = {},
) {
    val verticalScrollState = rememberScrollState(0)

    SimpleCalculatorBoxWithConstraints { constraints ->
        with (LocalDensity.current) {
            Column(modifier = Modifier.onGloballyPositioned { onChangeHeight(it.size.height.toDp()) }) {
                FrontLayerHeader(headerHeight, isConcealed, onClickIcon)
                Divider(constraints.padding(horizontal = 8.dp))
                Box(modifier = constraints.verticalScroll(verticalScrollState)) {
                    SimpleCalculateResult()
                }
            }
        }
    }
}

@Composable
private fun FrontLayerHeader(
    headerHeight: Dp,
    isConcealed: Boolean? = null,
    onClickIcon: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
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
        if (isConcealed != null) {
            IconButton(onClick = onClickIcon) {
                val (icon, description) =
                    if (isConcealed)
                        Icons.Default.KeyboardArrowUp to "reveal"
                    else
                        Icons.Default.KeyboardArrowDown to "conceal"

                Icon(icon, contentDescription = description)
            }
        }
    }
}
