@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.molecules.appbar

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.Strings
import net.subroh0508.wingcalculator.composeui.components.getString

@Composable
fun TopAppBarWithNavigation(
    text: String? = null,
    onNavigationClick: (() -> Unit)? = null,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    contentPadding: PaddingValues = AppBarDefaults.ContentPadding,
    modifier: Modifier = Modifier,
    actions: @Composable () -> Unit = {},
) = TopAppBar(
    backgroundColor = backgroundColor,
    elevation = elevation,
    contentPadding = contentPadding,
    modifier = modifier,
) {
    if (onNavigationClick != null)
        IconButton(
            onClick = onNavigationClick,
            modifier = Modifier.padding(4.dp),
        ) {
            Icon(
                Icons.Default.Menu,
                contentDescription = getString(Strings.IconDescriptionTopAppBarNavigation),
                modifier = Modifier.size(24.dp),
            )
        }
    else
        Spacer(Modifier.width(16.dp))
    Text(
        text ?: "",
        color = MaterialTheme.colors.onSurface.copy(alpha = if (text == null) 0.6F else 1.0F),
        modifier = Modifier.weight(1F)
            .padding(end = 16.dp),
    )

    actions()
}
