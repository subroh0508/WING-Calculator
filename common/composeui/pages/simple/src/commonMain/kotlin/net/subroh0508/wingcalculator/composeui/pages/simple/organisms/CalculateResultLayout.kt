@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import net.subroh0508.wingcalculator.composeui.components.di.uiModel
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculateresultlayout.AppealType
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculateresultlayout.CalculateResultTables

@Composable
fun CalculatorResultLayout(
    modifier: Modifier = Modifier,
) {
    val uiModel = SimpleCalculatorProviderContext.current.uiModel
    if (uiModel.isResultTableHidden) return

    var appealType by remember { mutableStateOf(AppealType.VOCAL) }

    Box(modifier) {
        CalculateResultTables(
            appealType,
            modifier = Modifier.align(Alignment.Center),
        ) { appealType = it }
    }
}
