package net.subroh0508.wingcalculator.composeui.pages.simple.templates.backdrop

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.ui.Modifier
import net.subroh0508.wingcalculator.composeui.pages.simple.model.PanelMinWidth

val BoxWithConstraintsScope.widthConstraintsModifier get() = when {
    maxWidth < (PanelMinWidth * 2) -> Modifier.requiredWidthIn(min = PanelMinWidth)
    else -> Modifier.requiredWidthIn(max = PanelMinWidth * 2)
}
