@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.atoms.list.DeletableListItem
import net.subroh0508.wingcalculator.composeui.components.atoms.list.Footer
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.CollapsingTopAppBarContainer
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.TopAppSearchBarHeight
import net.subroh0508.wingcalculator.composeui.components.molecules.list.LazyColumnWithFooter
import net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers.provideDeletePresetDispatcher
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
                is SimpleCalculatorUiModel.Query.Opened -> PresetSuggests(constraints)
            }
        }
    }
}

@Composable
private fun ColumnScope.Forms(constraints: Modifier, frontLayerHeight: Dp) {
    CalculatorForm(constraints)
    Spacer(Modifier.height(frontLayerHeight + 32.dp))
}
