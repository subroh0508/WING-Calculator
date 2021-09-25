@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates.backdrop

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.CalculatorResultLayout

@Composable
fun FrontLayerContent(
    onChangeHeight: (Dp) -> Unit = {},
    headerContent: @Composable ColumnScope.(Modifier) -> Unit = { Spacer(Modifier.height(56.dp)) },
    modifier: Modifier = Modifier,
) = BoxWithConstraints(modifier) {
    CalculatorResultLayout(
        headerContent,
        onChangeHeight,
        widthConstraintsModifier,
    )
}

