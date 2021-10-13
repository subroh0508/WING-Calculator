@file:JvmName("AndroidAppPreferencePageConstraints")

package net.subroh0508.wingcalculator.composeui.appframe.constraints

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.appframe.Pages
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.DrawerConstraints
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.DrawerType
import net.subroh0508.wingcalculator.composeui.pages.simple.model.PanelMinWidth
import net.subroh0508.wingcalculator.composeui.pages.simple.model.Panels

actual enum class AppPreferencePageConstraints(
    override val drawer: DrawerType,
    private val range: ClosedRange<Dp>,
) : LayoutConstraints, ClosedRange<Dp> by range {
    ONE_PANEL_MODAL(DrawerType.Modal, 0.dp..(AppPreferenceMinWidth * 2 - 1.dp)),
    ONE_PANEL_SHRINKABLE_MODAL(DrawerType.ShrinkableModal, (AppPreferenceMinWidth * 2)..Dp.Infinity),

    ONE_PANEL_SHRINKABLE_PERSIST(DrawerType.ShrinkablePersist, -Dp.Infinity..(-1).dp);

    actual companion object
}
