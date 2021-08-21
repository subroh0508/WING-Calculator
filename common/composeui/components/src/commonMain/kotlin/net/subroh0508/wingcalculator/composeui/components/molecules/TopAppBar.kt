@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.molecules

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopAppBar(
    onClickNavigation: () -> Unit,
    title: String? = null,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    modifier: Modifier = Modifier,
) = TopAppBar(
    backgroundColor = backgroundColor,
    modifier = modifier,
) {
    IconButton(
        onClick = onClickNavigation,
        modifier = Modifier.padding(4.dp),
    ) {
        Icon(
            Icons.Default.Menu,
            contentDescription = "navigation",
            modifier = Modifier.size(24.dp),
        )
    }
    Text(
        title ?: "",
        style = MaterialTheme.typography.h6,
        modifier = Modifier.weight(1F)
            .padding(start = 16.dp, end = 12.dp)
            .align(Alignment.CenterVertically),
    )
    actions()
    Spacer(modifier = Modifier.width(4.dp))
}
