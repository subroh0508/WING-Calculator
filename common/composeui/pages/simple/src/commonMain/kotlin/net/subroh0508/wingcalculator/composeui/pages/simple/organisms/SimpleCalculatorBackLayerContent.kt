@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.runtime.*
import androidx.compose.ui.unit.Dp
import net.subroh0508.wingcalculator.composeui.pages.simple.templates.SimpleCalculatorBoxWithConstraints

@Composable
fun SimpleCalculatorBackLayerContent(
    frontLayerHeight: Dp,
    onAppBarNavigationClick: () -> Unit,
) = SimpleCalculatorBoxWithConstraints { constraints ->
    PresetSearchBarLayout(
        frontLayerHeight,
        onAppBarNavigationClick,
        constraints,
    )
}
