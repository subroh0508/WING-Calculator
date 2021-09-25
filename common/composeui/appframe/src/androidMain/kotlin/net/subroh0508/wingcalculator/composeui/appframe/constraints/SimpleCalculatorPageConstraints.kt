@file:JvmName("AndroidSimpleCalculatorPageConstraints")

package net.subroh0508.wingcalculator.composeui.appframe.constraints

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.DrawerType
import net.subroh0508.wingcalculator.composeui.pages.simple.model.PanelMinWidth

actual enum class SimpleCalculatorPageConstraints(
    override val drawer: DrawerType,
    private val range: ClosedRange<Dp>,
) : LayoutConstraints, ClosedRange<Dp> by range {
    ONE_PANEL_MODAL(DrawerType.Modal, 0.dp..(PanelMinWidth * 2 - 1.dp)),
    TWO_PANELS_MODAL(DrawerType.ShrinkableModal, (PanelMinWidth * 2)..(PanelMinWidth * 2 + 47.dp)),
    TWO_PANELS_SHRINKABLE_MODAL(DrawerType.ShrinkableModal, (PanelMinWidth * 2 + 48.dp)..Dp.Infinity),

    ONE_PANEL_SHRINKABLE_MODAL(DrawerType.ShrinkableModal, -Dp.Infinity..(-1).dp),
    TWO_PANELS_SHRINKABLE_PERSIST(DrawerType.ShrinkablePersist, -Dp.Infinity..(-1).dp);

    actual companion object
}