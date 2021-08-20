@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.SimpleCalculateResult

@Composable
fun SimpleCalculatorFrontLayerContent(
    headerHeight: Dp,
) {
    val verticalScrollState = rememberScrollState(0)

    SimpleCalculatorBoxWithConstraints { constraints ->
        Column {
            Box(
                modifier = constraints.height(headerHeight)
                    .padding(horizontal = 16.dp),
            ) {
                Text(
                    "計算結果",
                    modifier = Modifier.align(Alignment.CenterStart),
                )
            }
            Divider(constraints.padding(horizontal = 8.dp))
            Box(modifier = constraints.verticalScroll(verticalScrollState)) {
                SimpleCalculateResult()
            }
        }
    }
}
