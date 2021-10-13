@file:JvmName("AndroidAppPreferencePageConstraints")

package net.subroh0508.wingcalculator.composeui.appframe.constraints

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.DrawerType

actual enum class AppPreferencePageConstraints(
    override val drawer: DrawerType,
    private val range: ClosedRange<Dp>,
) : LayoutConstraints, ClosedRange<Dp> by range {
    ONE_PANEL_MODAL(DrawerType.Modal, 0.dp..(AppPreferenceMinWidth + 47.dp)),
    ONE_PANEL_SHRINKABLE_MODAL(DrawerType.ShrinkableModal, (AppPreferenceMinWidth + 48.dp)..(AppPreferenceMinWidth + 255.dp)),
    ONE_PANEL_SHRINKABLE_PERSIST(DrawerType.ShrinkablePersist, (AppPreferenceMinWidth + 256.dp)..Dp.Infinity);

    actual companion object
}