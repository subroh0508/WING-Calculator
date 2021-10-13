package net.subroh0508.wingcalculator.composeui.appframe.constraints

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.appframe.Pages
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.DrawerConstraints
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.DrawerType
import net.subroh0508.wingcalculator.composeui.pages.simple.model.Panels

expect enum class AppPreferencePageConstraints : LayoutConstraints, ClosedRange<Dp> {
    ONE_PANEL_MODAL,
    ONE_PANEL_SHRINKABLE_MODAL,
    ONE_PANEL_SHRINKABLE_PERSIST;

    companion object
}

val AppPreferenceMinWidth = 320.dp

operator fun AppPreferencePageConstraints.Companion.invoke(maxWidth: Dp) =
    AppPreferencePageConstraints.values().find {
        maxWidth in it
    } ?: AppPreferencePageConstraints.ONE_PANEL_MODAL
