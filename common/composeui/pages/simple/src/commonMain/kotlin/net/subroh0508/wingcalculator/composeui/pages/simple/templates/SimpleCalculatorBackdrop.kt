@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.ShrinkableDrawerState
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.PresetSearchBarLayout

@Composable
expect fun SimpleCalculatorBackdrop(drawerState: ShrinkableDrawerState)

@Composable
fun BackLayerContent(
    frontLayerHeight: Dp,
    onAppBarNavigationClick: () -> Unit,
) = SimpleCalculatorBoxWithConstraints { constraints ->
    PresetSearchBarLayout(
        frontLayerHeight,
        onAppBarNavigationClick,
        constraints,
    )
}

