@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculatorform

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers.provideInputFormDispatcher
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculatorform.selector.MemoryLevelSelector

@Composable
fun ColumnScope.MemoryForm() {
    val (uiModel, dispatch) = provideInputFormDispatcher()

    val (_, _, _, _, _, _, _, memoryLevel) = uiModel.form

    Row {
        MemoryLevelSelector(
            memoryLevel,
            onChange = { dispatch(it) },
            modifier = Modifier.weight(1F)
        )
        Spacer(Modifier.width(8.dp))
        Spacer(Modifier.weight(1F))
    }
}
