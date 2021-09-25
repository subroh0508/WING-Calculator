@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import net.subroh0508.wingcalculator.composeui.components.di.uiModel
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.composeui.pages.simple.model.Panels
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculateresultlayout.AppealType
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculateresultlayout.CalculateResultTables

@Composable
fun ColumnScope.CalculatorResultLayout(
    headerContent: @Composable ColumnScope.(Modifier) -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiModel = SimpleCalculatorProviderContext.current.uiModel

    if (uiModel.panel == Panels.TWO) {
        ScrollableCalculateResultTables(
            *AppealType.values(),
            headerContent = headerContent,
            modifier = modifier,
        )

        return
    }

    if (uiModel.isResultTableHidden) return

    var appealType by remember { mutableStateOf(AppealType.VOCAL) }

    ScrollableCalculateResultTables(
        appealType,
        headerContent = headerContent,
        onAppealTypeChanged = { appealType = it },
        modifier = modifier,
    )
}

@Composable
private fun ColumnScope.ScrollableCalculateResultTables(
    vararg appealType: AppealType,
    headerContent: @Composable ColumnScope.(Modifier) -> Unit,
    onAppealTypeChanged: ((AppealType) -> Unit) = {},
    modifier: Modifier = Modifier,
) {
    val verticalScrollState = rememberScrollState(0)

    headerContent(modifier)
    Box(modifier.verticalScroll(verticalScrollState)) {
        CalculateResultTables(*appealType, onAppealTypeChanged = onAppealTypeChanged)
    }
}
