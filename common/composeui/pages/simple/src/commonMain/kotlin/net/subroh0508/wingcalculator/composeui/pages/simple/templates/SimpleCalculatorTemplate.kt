@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.SimpleBuffForm
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.SimpleCalculateResult
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.SimpleUnitForm

@Composable
fun SimpleCalculatorTemplate() {
    val verticalScrollState = rememberScrollState(0)

    BoxWithConstraints {
        val widthModifier = when {
            maxWidth < 480.dp -> Modifier.requiredSizeIn(minWidth = 320.dp)
            else -> Modifier.requiredSizeIn(maxWidth = 480.dp)
        }

        Column(
            modifier = Modifier.fillMaxHeight()
                .background(MaterialTheme.colors.background)
                .verticalScroll(verticalScrollState),
        ) {
            SimpleUnitForm(widthModifier)
            SimpleBuffForm(widthModifier)

            SimpleCalculateResult()
        }
    }
}
