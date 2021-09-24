package net.subroh0508.wingcalculator.composeui.appframe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.DrawerConstraints
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.DrawerType

sealed interface LayoutConstraints : DrawerConstraints

object DefaultLayoutConstraints : LayoutConstraints {
    override val drawer = DrawerType.ShrinkableModal
}

enum class SimpleCalculatorPageConstraints(
    override val drawer: DrawerType,
    val panelCounts: Int,
    private val range: ClosedRange<Dp>,
) : LayoutConstraints, ClosedRange<Dp> by range {
    ONE_PANEL_MODAL(DrawerType.Modal, 1, 0.dp..368.dp),
    ONE_PANEL_SHRINKABLE_MODAL(DrawerType.ShrinkableModal, 1, 369.dp..688.dp),
    TWO_PANELS_SHRINKABLE_MODAL(DrawerType.ShrinkableModal, 2, 689.dp..896.dp),
    TWO_PANELS_SHRINKABLE_PERSIST(DrawerType.ShrinkablePersist, 2, 897.dp..Dp.Infinity);

    companion object {
        operator fun invoke(maxWidth: Dp) = when (maxWidth) {
            in ONE_PANEL_MODAL -> ONE_PANEL_MODAL
            in ONE_PANEL_SHRINKABLE_MODAL -> ONE_PANEL_SHRINKABLE_MODAL
            in TWO_PANELS_SHRINKABLE_MODAL -> TWO_PANELS_SHRINKABLE_MODAL
            else -> TWO_PANELS_SHRINKABLE_PERSIST
        }
    }
}
