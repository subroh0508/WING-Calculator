@file:JvmName("DesktopSimpleCalculatorPageConstraints")

package net.subroh0508.wingcalculator.composeui.appframe.constraints

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.DrawerType

actual enum class SimpleCalculatorPageConstraints(
    override val drawer: DrawerType,
    private val range: ClosedRange<Dp>,
) : LayoutConstraints, ClosedRange<Dp> by range {
    ONE_PANEL_MODAL(DrawerType.Modal, 0.dp..368.dp),
    ONE_PANEL_SHRINKABLE_MODAL(DrawerType.ShrinkableModal, 369.dp..688.dp),
    TWO_PANELS_SHRINKABLE_MODAL(DrawerType.ShrinkableModal, 689.dp..896.dp),
    TWO_PANELS_SHRINKABLE_PERSIST(DrawerType.ShrinkablePersist, 897.dp..Dp.Infinity),

    TWO_PANELS_MODAL(DrawerType.Modal, -Dp.Infinity..(-1).dp);

    actual companion object
}
