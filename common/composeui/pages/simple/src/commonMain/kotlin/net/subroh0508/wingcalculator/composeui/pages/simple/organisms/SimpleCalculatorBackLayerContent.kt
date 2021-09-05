@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.imports.DropdownMenu
import net.subroh0508.wingcalculator.composeui.components.imports.DropdownMenuItem
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.CollapsingTopAppBarContainer
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.SearchBarState
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.TopAppSearchBar
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.TopAppSearchBarHeight
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.composeui.pages.simple.provideSearchPresetDispatcher
import net.subroh0508.wingcalculator.composeui.pages.simple.templates.SimpleCalculatorBoxWithConstraints

@Composable
fun SimpleCalculatorBackLayerContent(
    frontLayerHeight: Dp,
    onAppBarNavigationClick: () -> Unit,
) {
    val (uiModel, dispatch) = provideSearchPresetDispatcher()
    var expandSaveMenu by remember { mutableStateOf(false) }
    var saveMode by remember { mutableStateOf<MenuForSave?>(null) }

    val (_, query, suggestions) = uiModel

    when (saveMode) {
        MenuForSave.CREATE -> SimplePresetCreateDialog { saveMode = null }
        MenuForSave.UPDATE -> SimplePresetUpdateDialog { saveMode = null }
    }

    SimpleCalculatorBoxWithConstraints { constraints ->
        CollapsingTopAppBarContainer(
            appBar = { appBarModifier ->
                TopAppSearchBar(
                    query.text,
                    query.toSearchBarState(),
                    onNavigationClick = onAppBarNavigationClick,
                    onSearchBarStateChange = {
                        dispatch(
                            when (it) {
                                SearchBarState.OPENED -> SimpleCalculatorUiModel.Query.Opened()
                                SearchBarState.CLOSED -> SimpleCalculatorUiModel.Query.Closed
                            },
                        )
                    },
                    onQueryChange = { dispatch(it) },
                    backgroundColor = MaterialTheme.colors.background,
                    elevation = 0.dp,
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                    modifier = appBarModifier.then(constraints),
                ) {
                    IconButton(
                        onClick = { expandSaveMenu = true },
                        modifier = Modifier.padding(4.dp),
                    ) {
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = "menu_save",
                            modifier = Modifier.size(24.dp),
                        )

                        MenuForSave(
                            expandSaveMenu,
                            onClick = { saveMode = it },
                            onDismissRequest = { expandSaveMenu = false },
                        )
                    }
                }
            },
            appBarHeight = TopAppSearchBarHeight,
            isCollapsingEnable = query is SimpleCalculatorUiModel.Query.Closed,
        ) {
            when (query) {
                is SimpleCalculatorUiModel.Query.Closed -> Forms(
                    constraints,
                    frontLayerHeight,
                )
                is SimpleCalculatorUiModel.Query.Opened -> SuggestList(
                    constraints,
                    query.text,
                    suggestions,
                ) { dispatch(it) }
            }
        }
    }
}

private enum class MenuForSave(val label: String) {
    CREATE("新規作成"), UPDATE("更新")
}

@Composable
private fun MenuForSave(
    expanded: Boolean,
    onClick: (MenuForSave) -> Unit,
    onDismissRequest: () -> Unit,
) = DropdownMenu(
    expanded,
    onDismissRequest = onDismissRequest,
) {
    MenuForSave.values().forEachIndexed { i, item ->
        DropdownMenuItem(
            onClick = {
                onDismissRequest()
                onClick(item)
            },
        ) {
            Text(item.label)
        }
    }
}

@Composable
private fun ColumnScope.Forms(constraints: Modifier, frontLayerHeight: Dp) {
    SimpleUnitForm(constraints)
    Divider(constraints.padding(top = 24.dp, bottom = 16.dp, start = 8.dp, end = 8.dp))
    SimpleBuffForm(constraints)
    Spacer(Modifier.height(frontLayerHeight + 32.dp))
}

@Composable
private fun ColumnScope.SuggestList(
    constraints: Modifier,
    query: String?,
    suggestions: List<Pair<Long, SimpleCalculatorUiModel.Form>>,
    onClick: (Pair<Long, SimpleCalculatorUiModel.Form>) -> Unit,
) {
    Divider(constraints)
    if (query != null && suggestions.isEmpty())
        Text(
            "条件に一致するプリセットが見つかりません",
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
        )
    else
        LazyColumn(modifier = constraints) {
            suggestions.forEachIndexed { index, (id, form) ->
                item(index) {
                    ListItem(
                        text = { Text(form.name ?: "") },
                        modifier = Modifier.clickable { onClick(id to form) },
                    )
                }
            }
        }
}

private fun SimpleCalculatorUiModel.Query.toSearchBarState() = when (this) {
    is SimpleCalculatorUiModel.Query.Opened -> SearchBarState.OPENED
    is SimpleCalculatorUiModel.Query.Closed -> SearchBarState.CLOSED
}
