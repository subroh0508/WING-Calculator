@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.ResponsibleDrawerState
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.PresetSearchBarLayout

@Composable
expect fun SimpleCalculatorBackdrop(drawerState: ResponsibleDrawerState)

@Composable
fun BackLayerContent(
    frontLayerHeight: Dp = 0.dp,
    modifier: Modifier = Modifier,
    onAppBarNavigationClick: () -> Unit = {},
) = SimpleCalculatorBoxWithConstraints(modifier) { constraints ->
    PresetSearchBarLayout(
        frontLayerHeight,
        onAppBarNavigationClick,
        constraints,
    )
}

