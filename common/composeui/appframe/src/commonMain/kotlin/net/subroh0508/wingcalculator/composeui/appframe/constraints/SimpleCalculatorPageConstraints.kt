package net.subroh0508.wingcalculator.composeui.appframe.constraints

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.appframe.Pages
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.DrawerConstraints
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.DrawerType
import net.subroh0508.wingcalculator.composeui.pages.simple.model.Panels

expect enum class SimpleCalculatorPageConstraints : LayoutConstraints, ClosedRange<Dp> {
    ONE_PANEL_MODAL,
    ONE_PANEL_SHRINKABLE_MODAL,
    TWO_PANELS_MODAL,
    TWO_PANELS_SHRINKABLE_MODAL,
    TWO_PANELS_SHRINKABLE_PERSIST;

    companion object
}

operator fun SimpleCalculatorPageConstraints.Companion.invoke(maxWidth: Dp) =
    SimpleCalculatorPageConstraints.values().find {
        maxWidth in it
    } ?: SimpleCalculatorPageConstraints.ONE_PANEL_MODAL

val SimpleCalculatorPageConstraints.panel get() = Panels.valueOf(name.split("_")[0])
