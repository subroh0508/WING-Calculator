@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.CollapsingTopAppBarContainer
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.SearchBarState
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.TopAppSearchBar
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.TopAppSearchBarHeight
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorDispatcherContext
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.composeui.pages.simple.templates.SimpleCalculatorBoxWithConstraints

@Composable
fun SimpleCalculatorBackLayerContent(
    frontLayerHeight: Dp,
    onAppBarNavigationClick: () -> Unit,
) {
    val uiModel = SimpleCalculatorProviderContext.current
    val onChangeUiModel = SimpleCalculatorDispatcherContext.current

    val (_, query, suggestions) = uiModel

    SimpleCalculatorBoxWithConstraints { constraints ->
        CollapsingTopAppBarContainer(
            appBar = { appBarModifier ->
                TopAppSearchBar(
                    query.text,
                    query.toSearchBarState(),
                    onNavigationClick = onAppBarNavigationClick,
                    onSearchBarStateChange = {
                        onChangeUiModel(
                            uiModel.copy(
                                query = when (it) {
                                    SearchBarState.OPENED -> SimpleCalculatorUiModel.Query.Opened()
                                    SearchBarState.CLOSED -> SimpleCalculatorUiModel.Query.Closed
                                },
                            ),
                        )
                    },
                    onQueryChange = { onChangeUiModel(uiModel.input(it)) },
                    backgroundColor = MaterialTheme.colors.background,
                    elevation = 0.dp,
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                    modifier = appBarModifier.then(constraints),
                ) {
                    IconButton(
                        onClick = {},
                        modifier = Modifier.padding(4.dp),
                    ) {
                        Icon(
                            Icons.Default.Save,
                            contentDescription = "save",
                            modifier = Modifier.size(24.dp),
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
                ) { onChangeUiModel(uiModel.select(it)) }
            }
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
    suggestions: List<Pair<String, SimpleCalculatorUiModel.Form>>,
    onClick: (Pair<String, SimpleCalculatorUiModel.Form>) -> Unit,
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
            suggestions.forEachIndexed { index, (name, form) ->
                item(index) {
                    ListItem(
                        text = { Text(name) },
                        modifier = Modifier.clickable { onClick(name to form) },
                    )
                }
            }
        }
}

private fun SimpleCalculatorUiModel.Query.toSearchBarState() = when (this) {
    is SimpleCalculatorUiModel.Query.Opened -> SearchBarState.OPENED
    is SimpleCalculatorUiModel.Query.Closed -> SearchBarState.CLOSED
}
