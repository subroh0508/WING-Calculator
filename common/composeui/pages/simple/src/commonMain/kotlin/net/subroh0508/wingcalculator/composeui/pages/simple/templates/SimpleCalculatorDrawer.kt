@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates

import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable

@Composable
fun SimpleCalculatorDrawer() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalDrawer(
        drawerContent = {

        },
        drawerState = drawerState,
    ) {
        SimpleCalculatorBackdrop(drawerState)
    }
}
