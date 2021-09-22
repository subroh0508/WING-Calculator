@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.atoms.backdrop.FrontLayerHeader
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculateresultlayout.CalculateResultTable
import net.subroh0508.wingcalculator.composeui.pages.simple.templates.SimpleCalculatorBoxWithConstraints

@Composable
fun CalculatorResultLayout(
    headerHeight: Dp,
    onChangeHeight: (Dp) -> Unit,
    isConcealed: Boolean? = null,
    onClickIcon: () -> Unit = {},
) {
    val (_, uiModel) = SimpleCalculatorProviderContext.current

    val verticalScrollState = rememberScrollState(0)

    if (uiModel.query is SimpleCalculatorUiModel.Query.Opened) {
        return
    }

    SimpleCalculatorBoxWithConstraints { constraints ->
        with (LocalDensity.current) {
            Column(modifier = Modifier.onGloballyPositioned {
                onChangeHeight(it.size.height.toDp())
            }) {
                FrontLayerHeader("計算結果", headerHeight, isConcealed, onClickIcon)
                Divider(constraints.padding(horizontal = 8.dp))
                Box(modifier = constraints.verticalScroll(verticalScrollState)) {
                    CalculateResultTable()
                }
            }
        }
    }
}