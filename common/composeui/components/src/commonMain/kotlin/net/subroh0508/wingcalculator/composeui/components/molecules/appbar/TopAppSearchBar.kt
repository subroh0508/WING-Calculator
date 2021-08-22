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

val TopAppSearchBarHeight = 64.dp

@Composable
fun TopAppSearchBar(
    text: String? = null,
    onClickNavigation: () -> Unit,
    onClickSearchBar: () -> Unit,
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
    OutlinedButton(
        onClick = onClickSearchBar,
        contentPadding = PaddingValues(horizontal = 0.dp, vertical = 8.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colors.onSurface,
        ),
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
            text ?: "プリセットを検索",
            color = MaterialTheme.colors.onSurface.copy(
                if (text != null) 1.0F else 0.6F,
            ),
            modifier = Modifier.weight(1F)
                .padding(end = 16.dp),
        )

        actions()
    }
}
