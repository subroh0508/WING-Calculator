@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates

import androidx.compose.material.DrawerState
import androidx.compose.runtime.Composable

@Composable
expect fun SimpleCalculatorBackdrop(drawerState: DrawerState)
