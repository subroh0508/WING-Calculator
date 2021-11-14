@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms.presetsearchbarlayout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.subroh0508.wingcalculator.composeui.components.atoms.list.DeletableListItem
import net.subroh0508.wingcalculator.composeui.components.atoms.list.Footer
import net.subroh0508.wingcalculator.composeui.components.molecules.list.LazyColumnWithFooter
import net.subroh0508.wingcalculator.composeui.pages.simple.Strings
import net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers.provideDeletePresetDispatcher
import net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers.provideSelectPresetDispatcher
import net.subroh0508.wingcalculator.composeui.pages.simple.getString

@Composable
fun ColumnScope.PresetSuggests(
    modifier: Modifier = Modifier,
) {
    val (uiModel, select) = provideSelectPresetDispatcher()
    val (_, delete) = provideDeletePresetDispatcher()

    val (_, query, suggestions) = uiModel

    Divider(modifier)
    LazyColumnWithFooter(
        suggestions,
        modifier = modifier,
        footer = {
            Footer(
                query.text != null && suggestions.isEmpty(),
                getString(Strings.MessageSearchPresetSuggestsEmpty),
            )
        }
    ) { _, (id, form) ->
        DeletableListItem(
            onDeleteClick = { delete(id) },
            modifier = Modifier.clickable { select(id to form) },
        ) { Text(form.name ?: "") }
    }
}