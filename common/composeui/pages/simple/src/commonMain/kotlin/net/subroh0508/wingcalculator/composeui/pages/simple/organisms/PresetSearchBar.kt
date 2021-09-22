@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.SearchBarState
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.TopAppSearchBar
import net.subroh0508.wingcalculator.composeui.components.molecules.menu.DropdownMenuItem
import net.subroh0508.wingcalculator.composeui.components.molecules.menu.ExpandableDropdownMenu
import net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers.provideSearchPresetDispatcher
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel

private enum class MenuForSave(override val label: String) : DropdownMenuItem {
    CREATE("新規作成"), UPDATE("更新")
}

@Composable
fun PresetSearchBar(
    onNavigationClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val (uiModel, dispatch) = provideSearchPresetDispatcher()
    var saveMode by remember { mutableStateOf<MenuForSave?>(null) }

    val (form, query, suggestions) = uiModel

    when (saveMode) {
        MenuForSave.CREATE -> SimplePresetCreateDialog { saveMode = null }
        MenuForSave.UPDATE -> SimplePresetUpdateDialog { saveMode = null }
    }

    TopAppSearchBar(
        uiModel.searchBarText,
        query.toSearchBarState(),
        onNavigationClick = onNavigationClick,
        onSearchBarStateChange = {
            dispatch(
                when (it) {
                    SearchBarState.OPENED -> SimpleCalculatorUiModel.Query.Opened(form.name)
                    SearchBarState.CLOSED -> SimpleCalculatorUiModel.Query.Closed
                },
            )
        },
        onQueryChange = { dispatch(it) },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp,
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        modifier = modifier,
    ) {
        val items = MenuForSave.values().filter {
            uiModel.isSelectedSuggestion || it != MenuForSave.UPDATE
        }

        ExpandableDropdownMenu(items, onClick = { saveMode = it })
    }
}

private fun SimpleCalculatorUiModel.Query.toSearchBarState() = when (this) {
    is SimpleCalculatorUiModel.Query.Opened -> SearchBarState.OPENED
    is SimpleCalculatorUiModel.Query.Closed -> SearchBarState.CLOSED
}

private val SimpleCalculatorUiModel.searchBarText get() = when (query) {
    is SimpleCalculatorUiModel.Query.Opened -> query.text
    is SimpleCalculatorUiModel.Query.Closed -> form.name
}
private val SimpleCalculatorUiModel.isSelectedSuggestion get() = form.id != null
