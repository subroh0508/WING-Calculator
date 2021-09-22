@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.CollapsingTopAppBarLayout
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.TopAppSearchBarHeight
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.presetsearchbarlayout.PresetSearchBar
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.presetsearchbarlayout.PresetSuggests

@Composable
fun PresetSearchBarLayout(
    frontLayerHeight: Dp,
    onAppBarNavigationClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val (_, uiModel) = SimpleCalculatorProviderContext.current

    val (_, query, _) = uiModel

    CollapsingTopAppBarLayout(
        appBar = { AppBarContent(onAppBarNavigationClick, it.then(modifier)) },
        appBarHeight = TopAppSearchBarHeight,
        isCollapsingEnable = query is SimpleCalculatorUiModel.Query.Closed,
    ) { AppBarLayoutContent(query, frontLayerHeight, modifier) }
}

@Composable
private fun AppBarContent(
    onAppBarNavigationClick: () -> Unit,
    modifier: Modifier = Modifier,
) = PresetSearchBar(onAppBarNavigationClick, modifier = modifier)

@Composable
private fun ColumnScope.AppBarLayoutContent(
    query: SimpleCalculatorUiModel.Query,
    frontLayerHeight: Dp,
    modifier: Modifier = Modifier,
) = when (query) {
    is SimpleCalculatorUiModel.Query.Closed -> CalculatorFormWithPadding(frontLayerHeight, modifier)
    is SimpleCalculatorUiModel.Query.Opened -> PresetSuggests(modifier)
}

@Composable
private fun ColumnScope.CalculatorFormWithPadding(
    frontLayerHeight: Dp,
    modifier: Modifier = Modifier,
) {
    CalculatorForm(modifier)
    Spacer(Modifier.height(frontLayerHeight + 32.dp))
}
