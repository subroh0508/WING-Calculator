@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates

import androidx.compose.material.BackdropScaffoldDefaults
import androidx.compose.runtime.Composable
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.RESULT_COMPONENT_HEIGHT

val  FRONT_LAYER_HEIGHT = RESULT_COMPONENT_HEIGHT + BackdropScaffoldDefaults.HeaderHeight

@Composable
expect fun SimpleCalculatorBackdrop()
