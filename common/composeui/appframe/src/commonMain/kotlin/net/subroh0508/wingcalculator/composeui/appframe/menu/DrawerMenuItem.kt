@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.appframe.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.ResponsiveDrawerState

private val DefaultMenuItemHeight = 56.dp

@Composable
fun DrawerMenuItem(
    icon: Pair<ImageVector, String>,
    label: String,
    drawerState: ResponsiveDrawerState,
    onClick: () -> Unit,
) = DrawerMenuItem(
    icon,
    label = {
        Text(
            label,
            softWrap = false,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.align(Alignment.CenterVertically),
        )
    },
    drawerState,
    onClick,
)

@Composable
fun DrawerMenuItem(
    icon: Pair<ImageVector, String>,
    label: @Composable RowScope.() -> Unit,
    drawerState: ResponsiveDrawerState,
    onClick: () -> Unit,
) = when (drawerState.currentValue) {
    DrawerValue.Closed -> ClosedDrawerMenuItem(icon, label, onClick)
    DrawerValue.Open -> OpenDrawerMenuItem(icon, label, drawerState, onClick)
}

@Composable
private fun ClosedDrawerMenuItem(
    icon: Pair<ImageVector, String>,
    label: @Composable RowScope.() -> Unit,
    onClick: () -> Unit,
) = Row(
    Modifier.fillMaxWidth()
        .height(DefaultMenuItemHeight)
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.align(Alignment.CenterVertically),
    ) {
        val (image, contentDescription) = icon

        Icon(image, contentDescription = contentDescription)
    }

    label()
    Spacer(Modifier.width(16.dp))
}

@Composable
private fun OpenDrawerMenuItem(
    icon: Pair<ImageVector, String>,
    label: @Composable RowScope.() -> Unit,
    drawerState: ResponsiveDrawerState,
    onClick: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val (image, contentDescription) = icon

    Row(
        Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(true),
        ) {
            scope.launch { drawerState.close() }
            onClick()
        }
            .fillMaxWidth()
            .height(DefaultMenuItemHeight),
    ) {
        Icon(
            image,
            contentDescription = contentDescription,
            modifier = Modifier.padding(12.dp)
                .align(Alignment.CenterVertically),
        )
        label()
        Spacer(Modifier.width(16.dp))
    }
}
