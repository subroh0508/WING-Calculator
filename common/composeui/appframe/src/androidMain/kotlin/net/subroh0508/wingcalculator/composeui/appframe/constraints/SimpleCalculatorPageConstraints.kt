@file:JvmName("AndroidSimpleCalculatorPageConstraints")

package net.subroh0508.wingcalculator.composeui.appframe.constraints

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.DrawerType

actual operator fun SimpleCalculatorPageConstraints.Companion.invoke(maxWidth: Dp) = when (maxWidth) {
    in SimpleCalculatorPageConstraints.ONE_PANEL_MODAL,
    in SimpleCalculatorPageConstraints.ONE_PANEL_SHRINKABLE_MODAL -> SimpleCalculatorPageConstraints.ONE_PANEL_MODAL
    else -> SimpleCalculatorPageConstraints.TWO_PANELS_SHRINKABLE_MODAL
}