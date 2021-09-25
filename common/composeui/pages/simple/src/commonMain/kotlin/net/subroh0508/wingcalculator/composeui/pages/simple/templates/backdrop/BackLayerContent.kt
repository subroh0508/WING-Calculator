@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates.backdrop

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.PresetSearchBarLayout

@Composable
fun BackLayerContent(
    frontLayerHeight: Dp = 0.dp,
    modifier: Modifier = Modifier,
    onAppBarNavigationClick: () -> Unit = {},
) = BoxWithConstraints(modifier) {
    PresetSearchBarLayout(
        frontLayerHeight,
        onAppBarNavigationClick,
        widthConstraintsModifier,
    )
}
