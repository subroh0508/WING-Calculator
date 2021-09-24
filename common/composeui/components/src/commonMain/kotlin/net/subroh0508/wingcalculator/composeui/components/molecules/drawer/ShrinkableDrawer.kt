@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.molecules.drawer

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private val SHRINKABLE_DRAWER_CONTENT_SHRINK_WIDTH = 48.dp
private val SHRINKABLE_DRAWER_CONTENT_EXPANDED_WIDTH = 256.dp

enum class ShrinkableDrawerValue { Shrink, Expand }

@Composable
fun ShrinkableDrawer(
    drawerContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    drawerState: ShrinkableDrawerValue = ShrinkableDrawerValue.Shrink,
    drawerBackgroundColor: Color = MaterialTheme.colors.surface,
    drawerContentColor: Color = contentColorFor(drawerBackgroundColor),
    content: @Composable () -> Unit,
) {
    val transition = updateTransition(drawerState)
    val drawerContentWidth by transition.animateDp { state ->
        when (state) {
            ShrinkableDrawerValue.Shrink -> SHRINKABLE_DRAWER_CONTENT_SHRINK_WIDTH
            ShrinkableDrawerValue.Expand -> SHRINKABLE_DRAWER_CONTENT_EXPANDED_WIDTH
        }
    }

    BoxWithConstraints(modifier.fillMaxSize()) {
        val drawerConstraints = constraints
        val (drawerWidth, drawerHeight) = with (LocalDensity.current) {
            listOf(drawerConstraints.maxWidth.toDp(), drawerConstraints.maxHeight.toDp())
        }

        Row(
            Modifier.sizeIn(
                minWidth = drawerWidth,
                maxWidth = drawerWidth,
                minHeight = drawerHeight,
                maxHeight = drawerHeight,
            ),
        ) {
            Surface(
                modifier = Modifier.width(drawerContentWidth)
                    .heightIn(min = drawerHeight, max = drawerHeight),
                color = drawerBackgroundColor,
                contentColor = drawerContentColor,
                content = drawerContent,
            )
            Divider(
                Modifier.width(1.dp)
                    .heightIn(min = drawerHeight, max = drawerHeight)
            )
            Box(
                Modifier.heightIn(min = drawerHeight, max = drawerHeight)
                    .fillMaxWidth(),
            ) {
                content()
            }
        }
    }
}

