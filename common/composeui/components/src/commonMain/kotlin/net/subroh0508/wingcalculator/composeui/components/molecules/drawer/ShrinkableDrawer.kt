@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.molecules.drawer

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

private val SHRINKABLE_DRAWER_CONTENT_SHRINK_WIDTH = 48.dp
private val SHRINKABLE_DRAWER_CONTENT_EXPANDED_WIDTH = 256.dp

enum class ShrinkableDrawerValue { Shrink, Expand }

class ShrinkableDrawerState(
    initValue: ShrinkableDrawerValue,
) {
    var currentValue: ShrinkableDrawerValue by mutableStateOf(initValue)
        private set

    fun open() { currentValue = ShrinkableDrawerValue.Expand }
    fun close() { currentValue = ShrinkableDrawerValue.Shrink }

    val isOpen get() = currentValue == ShrinkableDrawerValue.Expand
    val isClose get() = currentValue == ShrinkableDrawerValue.Shrink

    companion object {
        fun Saver() = Saver(
            save = { it.currentValue },
            restore = ::ShrinkableDrawerState,
        )
    }
}

@Composable
fun rememberShrinkableDrawerState(
    initValue: ShrinkableDrawerValue,
) = rememberSaveable(saver = ShrinkableDrawerState.Saver()) {
    ShrinkableDrawerState(initValue)
}

@Composable
fun ShrinkableDrawer(
    drawerContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    drawerState: ShrinkableDrawerState = rememberShrinkableDrawerState(ShrinkableDrawerValue.Shrink),
    drawerBackgroundColor: Color = MaterialTheme.colors.surface,
    drawerContentColor: Color = contentColorFor(drawerBackgroundColor),
    content: @Composable () -> Unit,
) {
    val transition = updateTransition(drawerState.currentValue)
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

