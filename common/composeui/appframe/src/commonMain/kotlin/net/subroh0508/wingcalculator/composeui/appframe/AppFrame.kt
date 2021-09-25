@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.appframe

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import net.subroh0508.wingcalculator.composeui.appframe.constraints.panel
import net.subroh0508.wingcalculator.composeui.appframe.menu.DrawerHeader
import net.subroh0508.wingcalculator.composeui.appframe.menu.DrawerMenuItem
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.ResponsiveDrawer
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.ResponsiveDrawerState
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.rememberResponsiveDrawerState
import net.subroh0508.wingcalculator.composeui.components.themes.AppTheme
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorPage

@Composable
fun AppFrame() = BoxWithConstraints {
    var page: Pages by rememberPage(maxWidth) { Pages.SimpleCalculator(maxWidth) }
    val drawerState = rememberResponsiveDrawerState(page.constraints, DrawerValue.Closed)

    ThemedAppFrame(page, drawerState) { page = it }
}

@Composable
private fun ThemedAppFrame(
    page: Pages,
    drawerState: ResponsiveDrawerState,
    onPageChanged: (Pages) -> Unit,
) = AppTheme {
    ResponsiveDrawer(
        page.constraints,
        drawerContent = {
            DrawerHeader(drawerState, page.constraints)
            Divider()
            DrawerMenuItem(
                Icons.Default.Settings to "Settings",
                "アプリ設定",
                drawerState,
            ) {}
        },
        drawerState = drawerState,
        content = {
            when (page) {
                is Pages.SimpleCalculator -> SimpleCalculatorPage(
                    page.constraints.panel,
                    page.constraints.drawer,
                    drawerState,
                )
            }
        },
    )
}
