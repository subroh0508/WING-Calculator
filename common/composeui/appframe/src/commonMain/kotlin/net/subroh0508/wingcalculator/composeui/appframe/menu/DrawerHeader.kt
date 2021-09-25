@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.appframe.menu

import androidx.compose.foundation.layout.*
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MenuOpen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.TopAppSearchBarHeight
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.DrawerConstraints
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.DrawerType
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.ResponsiveDrawerState

@Composable
fun ColumnScope.DrawerHeader(
    drawerState: ResponsiveDrawerState,
    constraints: DrawerConstraints,
) = when (drawerState.currentValue) {
    DrawerValue.Closed -> ClosedDrawerHeader(drawerState)
    DrawerValue.Open -> OpenDrawerHeader(drawerState, constraints)
}

@Composable
private fun ColumnScope.ClosedDrawerHeader(
    drawerState: ResponsiveDrawerState,
) {
    val scope = rememberCoroutineScope()

    IconButton(
        onClick = { scope.launch { drawerState.open() } },
        modifier = Modifier.height(TopAppSearchBarHeight)
            .padding(vertical = 16.dp),
    ) {
        Icon(
            Icons.Default.MenuOpen,
            contentDescription = "Open Drawer",
            modifier = Modifier.size(24.dp),
        )
    }
}

@Composable
private fun ColumnScope.OpenDrawerHeader(
    drawerState: ResponsiveDrawerState,
    constraints: DrawerConstraints,
) {
    if (constraints.drawer != DrawerType.Modal) {
        val scope = rememberCoroutineScope()

        IconButton(
            onClick = { scope.launch { drawerState.close() } },
            modifier = Modifier.height(TopAppSearchBarHeight)
                .padding(vertical = 16.dp)
                .align(Alignment.End),
        ) {
            Icon(
                Icons.Default.KeyboardArrowLeft,
                contentDescription = "Close Drawer",
                modifier = Modifier.size(24.dp),
            )
        }

        return
    }

    Spacer(Modifier.height(TopAppSearchBarHeight))
}
