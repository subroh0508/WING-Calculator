@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.di.uiModel
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.CollapsingTopAppBarLayout
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.TopAppSearchBarHeight
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.presetsearchbarlayout.PresetSearchBar
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.presetsearchbarlayout.PresetSuggests

@Composable
fun PresetSearchBarLayout(
    frontLayerHeight: Dp = 0.dp,
    onAppBarNavigationClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val query = SimpleCalculatorProviderContext.current.uiModel.query

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
    is SimpleCalculatorUiModel.Query.Closed -> CalculatorForm(frontLayerHeight, modifier)
    is SimpleCalculatorUiModel.Query.Opened -> PresetSuggests(modifier)
}
