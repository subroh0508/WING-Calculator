@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.molecules.appbar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val TopAppSearchBarHeight = 64.dp

enum class SearchBarState {
    CLOSED, OPENED
}

@Composable
fun TopAppSearchBar(
    text: String? = null,
    searchBarState: SearchBarState,
    onNavigationClick: () -> Unit,
    onSearchBarStateChange: (SearchBarState) -> Unit,
    onQueryChange: (String?) -> Unit,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    contentPadding: PaddingValues = AppBarDefaults.ContentPadding,
    modifier: Modifier = Modifier,
    actions: @Composable () -> Unit = {},
) = TopAppBar (
    backgroundColor = backgroundColor,
    elevation = elevation,
    contentPadding = contentPadding,
    modifier = modifier,
) {
    when (searchBarState) {
        SearchBarState.OPENED -> OpenedSearchBarContent(
            text,
            onArrowBackClick = { onSearchBarStateChange(SearchBarState.CLOSED) },
            onQueryChange = onQueryChange,
        )
        SearchBarState.CLOSED -> ClosedSearchBarContent(
            onNavigationClick,
            onSearchBarClick = { onSearchBarStateChange(SearchBarState.OPENED) },
            actions,
        )
    }
}

@Composable
private fun RowScope.OpenedSearchBarContent(
    text: String?,
    onArrowBackClick: () -> Unit,
    onQueryChange: (String?) -> Unit,
) {
    IconButton(
        onClick = onArrowBackClick,
        modifier = Modifier.padding(end = 8.dp),
    ) {
        Icon(
            Icons.Default.ArrowBack,
            contentDescription = "back",
            modifier = Modifier.size(24.dp),
        )
    }
    Box(modifier = Modifier.weight(1F)) {
        if (text == null) {
            Text(
                "プリセットを検索",
                style = MaterialTheme.typography.button,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6F),
                modifier = Modifier.padding(end = 16.dp),
            )
        }

        BasicTextField(
            text ?: "",
            onValueChange = { onQueryChange(it.takeIf(String::isNotBlank)) },
            // @see: https://github.com/JetBrains/compose-jb/issues/364
            // singleLine = true,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()
                .padding(end = 16.dp)
                .align(Alignment.CenterStart),
        )
    }
}

@Composable
private fun RowScope.ClosedSearchBarContent(
    onNavigationClick: () -> Unit,
    onSearchBarClick: () -> Unit,
    actions: @Composable () -> Unit = {},
) = OutlinedButton(
    onClick = onSearchBarClick,
    contentPadding = PaddingValues(horizontal = 0.dp, vertical = 8.dp),
    colors = ButtonDefaults.outlinedButtonColors(
        contentColor = MaterialTheme.colors.onSurface,
    ),
) {
    IconButton(
        onClick = onNavigationClick,
        modifier = Modifier.padding(4.dp),
    ) {
        Icon(
            Icons.Default.Menu,
            contentDescription = "navigation",
            modifier = Modifier.size(24.dp),
        )
    }
    Text(
        "プリセットを検索",
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.6F),
        modifier = Modifier.weight(1F)
            .padding(end = 16.dp),
    )

    actions()
}
