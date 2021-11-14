@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms.presetsearchbarlayout

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.SearchBarState
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.TopAppSearchBar
import net.subroh0508.wingcalculator.composeui.components.molecules.menu.DropdownMenuItem
import net.subroh0508.wingcalculator.composeui.components.molecules.menu.ExpandableDropdownMenu
import net.subroh0508.wingcalculator.composeui.pages.simple.Strings
import net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers.provideSearchPresetDispatcher
import net.subroh0508.wingcalculator.composeui.pages.simple.getString
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel

private enum class MenuForSave(private val strings: Strings) : DropdownMenuItem {
    CREATE(Strings.PresetSaveMenuCreate),
    UPDATE(Strings.PresetSaveMenuUpdate);

    override val label: String
        @Composable get() = getString(strings)
}

@Composable
fun PresetSearchBar(
    onNavigationClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val (uiModel, dispatch) = provideSearchPresetDispatcher()
    var saveMode by remember { mutableStateOf<MenuForSave?>(null) }

    val (_, query, _) = uiModel

    when (saveMode) {
        MenuForSave.CREATE -> SimplePresetCreateDialog { saveMode = null }
        MenuForSave.UPDATE -> SimplePresetUpdateDialog { saveMode = null }
    }

    TopAppSearchBar(
        uiModel.searchBarText,
        getString(Strings.PresetSearchBarPlaceholder),
        query.toSearchBarState(),
        onNavigationClick = if (uiModel.isOpenDrawerButtonVisible) onNavigationClick else null,
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

private val SimpleCalculatorUiModel.searchBarText @Composable get() = when (query) {
    is SimpleCalculatorUiModel.Query.Opened -> query.text
    is SimpleCalculatorUiModel.Query.Closed -> form.name?.let { getString(Strings.PresetSearchBarText, it) }
}
private val SimpleCalculatorUiModel.isSelectedSuggestion get() = form.id != null && form.name != null
