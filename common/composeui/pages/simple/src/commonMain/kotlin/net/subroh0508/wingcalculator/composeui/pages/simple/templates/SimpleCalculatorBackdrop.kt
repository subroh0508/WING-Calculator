@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates

import androidx.compose.runtime.Composable
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.ResponsiveDrawerState

@Composable
expect fun SimpleCalculatorBackdrop(drawerState: ResponsiveDrawerState, isResultTableHidden: Boolean)
