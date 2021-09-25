@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates.backdrop

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.CalculatorResultLayout

@Composable
fun FrontLayerContent(
    headerContent: @Composable ColumnScope.(Modifier) -> Unit = { Spacer(Modifier.height(56.dp)) },
    onHeightChange: (Dp) -> Unit = {},
    modifier: Modifier = Modifier,
) = BoxWithConstraints(modifier) {
    val constraint = widthConstraintsModifier

    GloballyPositionedColumn(onHeightChange) {
        CalculatorResultLayout(headerContent, constraint)
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
