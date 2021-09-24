@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates

import androidx.compose.runtime.Composable
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.ResponsibleDrawerState

@Composable
expect fun SimpleCalculatorBackdrop(drawerState: ResponsibleDrawerState)
