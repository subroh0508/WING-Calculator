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
import net.subroh0508.wingcalculator.composeui.components.di.uiModel
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculateresultlayout.CalculateResultTable
import net.subroh0508.wingcalculator.composeui.pages.simple.templates.SimpleCalculatorBoxWithConstraints

@Composable
fun CalculatorResultLayout(
    headerContent: @Composable ColumnScope.(Modifier) -> Unit,
    onChangeHeight: (Dp) -> Unit,
    modifier: Modifier = Modifier,
) {
    val verticalScrollState = rememberScrollState(0)

    if (SimpleCalculatorProviderContext.current.uiModel.isResultTableHidden) return

    with (LocalDensity.current) {
        Column(
            Modifier.onGloballyPositioned {
                onChangeHeight(it.size.height.toDp())
            }
        ) {
            headerContent(modifier)
            Box(modifier.verticalScroll(verticalScrollState)) {
                CalculateResultTable()
            }
        }
    }
}
