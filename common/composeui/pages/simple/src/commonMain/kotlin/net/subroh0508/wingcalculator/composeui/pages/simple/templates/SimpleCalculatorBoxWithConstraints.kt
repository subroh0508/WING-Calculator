@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SimpleCalculatorBoxWithConstraints(
    content: @Composable (Modifier) -> Unit,
) = BoxWithConstraints {
    val constraintsModifier = when {
        maxWidth < 640.dp -> Modifier.requiredSizeIn(minWidth = 320.dp)
        else -> Modifier.requiredSizeIn(maxWidth = 640.dp)
    }

    content(constraintsModifier)
}
