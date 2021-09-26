@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates.backdrop

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.TopAppSearchBarHeight
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.CalculatorResultLayout
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculateresultlayout.AppealType
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculateresultlayout.CalculateResultTables

@Composable
fun FrontLayerContent(
    headerContent: @Composable ColumnScope.() -> Unit,
    onHeightChange: (Dp) -> Unit,
    modifier: Modifier = Modifier,
) = BoxWithConstraints(modifier) {
    val verticalScrollState = rememberScrollState(0)
    val constraint = widthConstraintsModifier

    GloballyPositionedColumn(onHeightChange) {
        headerContent()
        CalculatorResultLayout(constraint.verticalScroll(verticalScrollState))
    }
}

private val CalculateResultTableMinHeight = 296.dp
private val ShowOneTableHeight = CalculateResultTableMinHeight * 1.5F

@Composable
fun FrontLayerContent(
    modifier: Modifier = Modifier,
) = BoxWithConstraints(modifier.fillMaxHeight()) {
    val verticalScrollState = rememberScrollState(0)
    val showOneTable = maxHeight < ShowOneTableHeight

    Column(widthConstraintsModifier.then(heightConstraintsModifier(verticalScrollState))) {
        when(showOneTable) {
            true -> CalculatorResultLayout(Modifier.fillMaxHeight())
            false -> {
                Spacer(Modifier.height(TopAppSearchBarHeight))
                CalculateResultTables(*AppealType.values())
            }
        }
    }
}

@Composable
private fun GloballyPositionedColumn(
    onHeightChange: (Dp) -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) = with (LocalDensity.current) {
    Column(
        Modifier.onGloballyPositioned { onHeightChange(it.size.height.toDp()) },
        content = content,
    )
}

private fun BoxWithConstraintsScope.heightConstraintsModifier(
    verticalScrollState: ScrollState,
) = when(maxHeight) {
    in CalculateResultTableMinHeight..ShowOneTableHeight -> Modifier.fillMaxHeight()
    else -> Modifier.verticalScroll(verticalScrollState)
}
