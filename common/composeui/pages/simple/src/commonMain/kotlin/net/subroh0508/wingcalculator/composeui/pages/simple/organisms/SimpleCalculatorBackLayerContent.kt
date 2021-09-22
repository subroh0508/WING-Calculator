@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.atoms.list.DeletableListItem
import net.subroh0508.wingcalculator.composeui.components.atoms.list.Footer
import net.subroh0508.wingcalculator.composeui.components.imports.DropdownMenu
import net.subroh0508.wingcalculator.composeui.components.imports.DropdownMenuItem
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.CollapsingTopAppBarContainer
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.SearchBarState
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.TopAppSearchBar
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.TopAppSearchBarHeight
import net.subroh0508.wingcalculator.composeui.components.molecules.list.LazyColumnWithFooter
import net.subroh0508.wingcalculator.composeui.components.molecules.menu.DropdownMenuItem
import net.subroh0508.wingcalculator.composeui.components.molecules.menu.ExpandableDropdownMenu
import net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers.provideDeletePresetDispatcher
import net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers.provideSearchPresetDispatcher
import net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers.provideSelectPresetDispatcher
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.composeui.pages.simple.templates.SimpleCalculatorBoxWithConstraints

@Composable
fun SimpleCalculatorBackLayerContent(
    frontLayerHeight: Dp,
    onAppBarNavigationClick: () -> Unit,
) {
    val (uiModel, dispatchSelect) = provideSelectPresetDispatcher()
    val (_, dispatchDelete) = provideDeletePresetDispatcher()

    val (_, query, suggestions) = uiModel

    SimpleCalculatorBoxWithConstraints { constraints ->
        CollapsingTopAppBarContainer(
            appBar = { appBarModifier ->
                PresetSearchBar(
                    onAppBarNavigationClick,
                    modifier = appBarModifier.then(constraints),
                )
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
                    onDeleteClick = { (id, _) -> dispatchDelete(id) },
                    onClick = { dispatchSelect(it) },
                )
            }
        }
    }
}

@Composable
private fun ColumnScope.Forms(constraints: Modifier, frontLayerHeight: Dp) {
    CalculatorForm(constraints)
    Spacer(Modifier.height(frontLayerHeight + 32.dp))
}

@Composable
private fun ColumnScope.SuggestList(
    constraints: Modifier,
    query: String?,
    suggestions: List<Pair<Long, SimpleCalculatorUiModel.Form>>,
    onClick: (Pair<Long, SimpleCalculatorUiModel.Form>) -> Unit,
    onDeleteClick: (Pair<Long, SimpleCalculatorUiModel.Form>) -> Unit,
) {
    Divider(constraints)
    LazyColumnWithFooter(
        suggestions,
        modifier = constraints,
        footer = {
            Footer(
                query != null && suggestions.isEmpty(),
                "条件に一致するプリセットが見つかりません",
            )
        }
    ) { _, (id, form) ->
        DeletableListItem(
            onDeleteClick = { onDeleteClick(id to form) },
            modifier = Modifier.clickable { onClick(id to form) },
        ) { Text(form.name ?: "") }
    }
}
