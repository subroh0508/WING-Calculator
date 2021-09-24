package net.subroh0508.wingcalculator.composeui.appframe.constraints

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.DrawerType
import net.subroh0508.wingcalculator.composeui.pages.simple.model.Panels

enum class SimpleCalculatorPageConstraints(
    override val drawer: DrawerType,
    val panel: Panels,
    private val range: ClosedRange<Dp>,
) : LayoutConstraints, ClosedRange<Dp> by range {
    ONE_PANEL_MODAL(DrawerType.Modal, Panels.ONE, 0.dp..368.dp),
    ONE_PANEL_SHRINKABLE_MODAL(DrawerType.ShrinkableModal, Panels.ONE, 369.dp..688.dp),
    TWO_PANELS_SHRINKABLE_MODAL(DrawerType.ShrinkableModal, Panels.TWO, 689.dp..896.dp),
    TWO_PANELS_SHRINKABLE_PERSIST(DrawerType.ShrinkablePersist, Panels.TWO, 897.dp..Dp.Infinity);

    companion object
}

expect operator fun SimpleCalculatorPageConstraints.Companion.invoke(maxWidth: Dp): SimpleCalculatorPageConstraints
