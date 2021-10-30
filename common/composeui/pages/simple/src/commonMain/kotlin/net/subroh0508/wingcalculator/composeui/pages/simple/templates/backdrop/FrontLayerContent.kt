@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates.backdrop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BackdropScaffoldDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.atoms.backdrop.FrontLayerHeader
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.CollapsingTopAppBarLayout
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.TopAppSearchBarHeight
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.AppealType
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.CalculateResultTable
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.CalculateResultTables

@Composable
fun FrontLayerContent(
    isConcealed: Boolean? = null,
    isResultTableHidden: Boolean,
    onHeightChange: (Dp) -> Unit,
    onHeaderIconClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) = BoxWithConstraints(modifier) {
    val verticalScrollState = rememberScrollState(0)
    val constraint = widthConstraintsModifier

    if (isResultTableHidden) return@BoxWithConstraints

    GloballyPositionedColumn(onHeightChange) {
        FrontLayerHeader(
            BackdropScaffoldDefaults.HeaderHeight,
            isConcealed = isConcealed,
            onIconClick = onHeaderIconClick,
        )
        CalculateResultTable(constraint.verticalScroll(verticalScrollState))
    }
}

private val CalculateResultTableMinHeight = 296.dp
private val ShowOneTableHeight = CalculateResultTableMinHeight * 1.5F

@Composable
fun FrontLayerContent(
    modifier: Modifier = Modifier,
) = BoxWithConstraints(modifier.fillMaxHeight()) {
    val showOneTable = maxHeight < ShowOneTableHeight
    val isCollapsingEnable = maxHeight in 0.dp..CalculateResultTableMinHeight || !showOneTable

    CollapsingTopAppBarLayout(
        appBar = {
            Column(it.background(MaterialTheme.colors.background)) {
                FrontLayerHeader(TopAppSearchBarHeight)
            }
        },
        appBarHeight = TopAppSearchBarHeight + 1.dp,
        isCollapsingEnable = isCollapsingEnable,
        modifier = widthConstraintsModifier,
    ) {
        when(showOneTable) {
            true -> CalculateResultTable(Modifier.fillMaxHeight())
            false -> CalculateResultTables(*AppealType.values())
        }
    }
}

@Composable
private fun GloballyPositionedColumn(
    onHeightChange: (Dp) -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) = with (LocalDensity.current) {
    Column(
        Modifier.onGloballyPositioned { onHeightChange(it.size.height.toDp()) },
        content = content,
    )
}

@Composable
private fun ColumnScope.FrontLayerHeader(
    headerHeight: Dp,
    isConcealed: Boolean? = null,
    onIconClick: () -> Unit = {},
) {
    FrontLayerHeader("計算結果", headerHeight, isConcealed, onClickIcon = onIconClick)
    Divider(Modifier.padding(horizontal = 8.dp))
}
