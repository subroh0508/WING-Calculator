@file:JvmName("DesktopSimpleCalculatorPageConstraints")

package net.subroh0508.wingcalculator.composeui.appframe.constraints

import androidx.compose.ui.unit.Dp

actual operator fun SimpleCalculatorPageConstraints.Companion.invoke(maxWidth: Dp) =
    SimpleCalculatorPageConstraints.values().find {
        maxWidth in it
    } ?: SimpleCalculatorPageConstraints.ONE_PANEL_MODAL
