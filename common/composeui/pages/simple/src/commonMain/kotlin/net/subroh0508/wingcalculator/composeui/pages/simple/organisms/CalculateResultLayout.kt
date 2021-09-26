@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    val verticalScrollState = rememberScrollState(0)

    if (uiModel.panel == Panels.TWO) {
        Column(modifier.verticalScroll(verticalScrollState)) {
            Spacer(Modifier.height(56.dp))
            CalculateResultTables(*AppealType.values())
        }

        return
    }

    if (uiModel.isResultTableHidden) return

    var appealType by remember { mutableStateOf(AppealType.VOCAL) }

    headerContent(modifier)
    Box(modifier.verticalScroll(verticalScrollState)) {
        CalculateResultTables(appealType) { appealType = it }
    }
}
