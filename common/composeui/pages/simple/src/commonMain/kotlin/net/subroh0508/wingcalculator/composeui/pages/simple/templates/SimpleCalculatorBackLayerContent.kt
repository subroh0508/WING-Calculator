@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.SimpleBuffForm
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.SimpleUnitForm

@Composable
fun SimpleCalculatorBackLayerContent(
    headerHeight: Dp,
) {
    val verticalScrollState = rememberScrollState(0)

    SimpleCalculatorBoxWithConstraints { constraints ->
        Column(
            modifier = Modifier.fillMaxHeight()
                .verticalScroll(verticalScrollState),
        ) {
            SimpleUnitForm(constraints)
            Divider(constraints.padding(top = 24.dp, bottom = 16.dp, start = 8.dp, end = 8.dp))
            SimpleBuffForm(constraints)
            Spacer(constraints.height(headerHeight))
        }
    }
}
