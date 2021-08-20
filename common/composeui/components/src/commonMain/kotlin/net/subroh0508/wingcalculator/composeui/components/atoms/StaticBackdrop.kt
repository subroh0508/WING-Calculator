@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.atoms

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BackdropScaffoldDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

@Composable
fun StaticBackdrop(
    appBar: @Composable () -> Unit,
    backLayerContent: @Composable () -> Unit,
    frontLayerContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    backLayerBackgroundColor: Color = MaterialTheme.colors.primary,
    backLayerContentColor: Color = contentColorFor(backLayerBackgroundColor),
    frontLayerShape: Shape = BackdropScaffoldDefaults.frontLayerShape,
    frontLayerElevation: Dp = BackdropScaffoldDefaults.FrontLayerElevation,
    frontLayerBackgroundColor: Color = MaterialTheme.colors.surface,
    frontLayerContentColor: Color = contentColorFor(frontLayerBackgroundColor),
) = Box(modifier) {
    Surface(
        color = backLayerBackgroundColor,
        contentColor = backLayerContentColor,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            appBar()
            backLayerContent()
        }
    }

    Surface(
        modifier = Modifier.fillMaxWidth()
            .align(Alignment.BottomCenter),
        shape = frontLayerShape,
        elevation = frontLayerElevation,
        color = frontLayerBackgroundColor,
        contentColor = frontLayerContentColor,
    ) {
        frontLayerContent()
    }
}
