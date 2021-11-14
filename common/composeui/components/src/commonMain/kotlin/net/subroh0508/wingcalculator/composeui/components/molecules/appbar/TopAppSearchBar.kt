@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.molecules.appbar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.Strings
import net.subroh0508.wingcalculator.composeui.components.getString

val TopAppSearchBarHeight = 64.dp

enum class SearchBarState {
    CLOSED, OPENED
}

@Composable
fun TopAppSearchBar(
    text: String? = null,
    placeHolder: String = "",
    searchBarState: SearchBarState,
    onSearchBarStateChange: (SearchBarState) -> Unit,
    onQueryChange: (String?) -> Unit,
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
    when (searchBarState) {
        SearchBarState.OPENED -> OpenedSearchBarContent(
            text, placeHolder,
            onArrowBackClick = { onSearchBarStateChange(SearchBarState.CLOSED) },
            onQueryChange = onQueryChange,
        )
        SearchBarState.CLOSED -> ClosedSearchBarContent(
            text, placeHolder,
            onSearchBarClick = { onSearchBarStateChange(SearchBarState.OPENED) },
            onNavigationClick,
            actions,
        )
    }
}

@Composable
private fun RowScope.OpenedSearchBarContent(
    text: String?,
    placeHolder: String,
    onArrowBackClick: () -> Unit,
    onQueryChange: (String?) -> Unit,
) {
    IconButton(
        onClick = onArrowBackClick,
        modifier = Modifier.padding(end = 8.dp),
    ) {
        Icon(
            Icons.Default.ArrowBack,
            contentDescription = getString(Strings.IconDescriptionOpenedSearchBarBack),
            modifier = Modifier.size(24.dp),
        )
    }
    Box(modifier = Modifier.weight(1F)) {
        if (text == null) {
            Text(
                placeHolder,
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
            textStyle = TextStyle.Default.copy(color = MaterialTheme.colors.onSurface),
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()
                .padding(end = 16.dp)
                .align(Alignment.CenterStart),
        )
    }
    IconButton(
        onClick = { onQueryChange(null) },
        modifier = Modifier.padding(start = 8.dp),
    ) {
        Icon(
            Icons.Default.Clear,
            contentDescription = getString(Strings.IconDescriptionOpenedSearchBarClear),
            modifier = Modifier.size(24.dp),
        )
    }
}

@Composable
private fun RowScope.ClosedSearchBarContent(
    text: String?,
    placeHolder: String,
    onSearchBarClick: () -> Unit,
    onNavigationClick: (() -> Unit)? = null,
    actions: @Composable () -> Unit = {},
) = OutlinedButton(
    onClick = onSearchBarClick,
    contentPadding = PaddingValues(horizontal = 0.dp, vertical = 8.dp),
    colors = ButtonDefaults.outlinedButtonColors(
        contentColor = MaterialTheme.colors.onSurface,
    ),
) {
    if (onNavigationClick != null)
        IconButton(
            onClick = onNavigationClick,
            modifier = Modifier.padding(4.dp),
        ) {
            Icon(
                Icons.Default.Menu,
                contentDescription = getString(Strings.IconDescriptionDropdownSelector),
                modifier = Modifier.size(24.dp),
            )
        }
    else
        Spacer(Modifier.width(16.dp))
    Text(
        text ?: placeHolder,
        color = MaterialTheme.colors.onSurface.copy(alpha = if (text == null) 0.6F else 1.0F),
        modifier = Modifier.weight(1F)
            .padding(end = 16.dp),
    )

    actions()
}
