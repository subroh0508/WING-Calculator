@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers.provideSavePresetDispatcher
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculatorform.BuffForm
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculatorform.CommentForm
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculatorform.MemoryForm
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculatorform.UnitStatusForm

private const val THROTTLED_TIME_MILLIS = 500L

@Composable
fun ColumnScope.CalculatorForm(
    frontLayerHeight: Dp,
    modifier: Modifier = Modifier,
) {
    AutoSaveEffect()

    Column(modifier.padding(horizontal = 8.dp)) {
        UnitStatusForm()
        Divider(modifier.padding(top = 24.dp, bottom = 16.dp))
        BuffForm()
        Divider(modifier.padding(top = 24.dp, bottom = 16.dp))
        MemoryForm()
        Spacer(Modifier.height(40.dp))
        CommentForm()
        Spacer(Modifier.height(frontLayerHeight + 32.dp))
    }
}

@Composable
private fun AutoSaveEffect() {
    val (uiModel, dispatch) = provideSavePresetDispatcher()

    LaunchedEffect(uiModel.form) {
        delay(THROTTLED_TIME_MILLIS)
        dispatch()
    }
}
