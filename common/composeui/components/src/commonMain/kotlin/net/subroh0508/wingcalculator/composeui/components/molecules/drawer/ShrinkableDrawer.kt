@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.molecules.drawer

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isUnspecified
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.Strings
import net.subroh0508.wingcalculator.composeui.components.getString

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

enum class ShrinkableDrawerVariant { Persist, Modal }

@Composable
fun PersistShrinkableDrawer(
    drawerContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    drawerState: ShrinkableDrawerState = rememberShrinkableDrawerState(ShrinkableDrawerValue.Shrink),
    drawerBackgroundColor: Color = MaterialTheme.colors.surface,
    drawerContentColor: Color = contentColorFor(drawerBackgroundColor),
    content: @Composable () -> Unit,
) = ShrinkableDrawer(
    ShrinkableDrawerVariant.Persist,
    drawerContent, modifier, drawerState, drawerBackgroundColor, drawerContentColor, content = content,
)

@Composable
fun ModalShrinkableDrawer(
    drawerContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    drawerState: ShrinkableDrawerState = rememberShrinkableDrawerState(ShrinkableDrawerValue.Shrink),
    drawerBackgroundColor: Color = MaterialTheme.colors.surface,
    drawerContentColor: Color = contentColorFor(drawerBackgroundColor),
    drawerElevation: Dp = DrawerDefaults.Elevation,
    scrimColor: Color = DrawerDefaults.scrimColor,
    content: @Composable () -> Unit,
) = ShrinkableDrawer(
    ShrinkableDrawerVariant.Modal,
    drawerContent, modifier, drawerState, drawerBackgroundColor, drawerContentColor, drawerElevation, scrimColor, content,
)

@Composable
fun ShrinkableDrawer(
    variant: ShrinkableDrawerVariant,
    drawerContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    drawerState: ShrinkableDrawerState,
    drawerBackgroundColor: Color,
    drawerContentColor: Color,
    drawerElevation: Dp = DrawerDefaults.Elevation,
    scrimColor: Color = DrawerDefaults.scrimColor,
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

        when (variant) {
            ShrinkableDrawerVariant.Persist -> PersistDrawerLayout(
                drawerWidth,
                drawerHeight,
                drawerContentWidth,
                drawerBackgroundColor,
                drawerContentColor,
                drawerContent,
                content,
            )
            ShrinkableDrawerVariant.Modal -> ModalDrawerLayout(
                drawerWidth,
                drawerHeight,
                drawerContentWidth,
                drawerBackgroundColor,
                drawerContentColor,
                drawerElevation,
                drawerState,
                drawerContent,
                scrimColor,
                content,
            )
        }
    }
}

@Composable
private fun PersistDrawerLayout(
    drawerWidth: Dp,
    drawerHeight: Dp,
    drawerContentWidth: Dp,
    drawerBackgroundColor: Color,
    drawerContentColor: Color,
    drawerContent: @Composable () -> Unit,
    content: @Composable () -> Unit,
) = Row(
    Modifier.sizeIn(
        minWidth = drawerWidth,
        maxWidth = drawerWidth,
        minHeight = drawerHeight,
        maxHeight = drawerHeight,
    ),
) {
    Surface(
        modifier = Modifier.width(drawerContentWidth)
            .heightIn(min = drawerHeight, max = drawerHeight)
            .borderEnd(
                MaterialTheme.colors.onSurface.copy(alpha = 0.12F),
                with (LocalDensity.current) { 1.dp.toPx() },
            ),
        color = drawerBackgroundColor,
        contentColor = drawerContentColor,
        content = drawerContent,
    )
    Box(
        Modifier.heightIn(min = drawerHeight, max = drawerHeight)
            .fillMaxWidth(),
    ) {
        content()
    }
}

@Composable
private fun ModalDrawerLayout(
    drawerWidth: Dp,
    drawerHeight: Dp,
    drawerContentWidth: Dp,
    drawerBackgroundColor: Color,
    drawerContentColor: Color,
    drawerElevation: Dp,
    drawerState: ShrinkableDrawerState,
    drawerContent: @Composable () -> Unit,
    scrimColor: Color,
    content: @Composable () -> Unit,
) = Box(
    Modifier.sizeIn(
        minWidth = drawerWidth,
        maxWidth = drawerWidth,
        minHeight = drawerHeight,
        maxHeight = drawerHeight,
    ),
) {
    val (borderAlpha, elevation) = if (drawerState.isOpen) 0F to drawerElevation else 0.12F to 0.dp

    Box(Modifier.padding(start = SHRINKABLE_DRAWER_CONTENT_SHRINK_WIDTH)) {
        content()
    }
    ModalScrim(drawerState.isOpen, scrimColor, drawerState::close)
    Surface(
        modifier = Modifier.width(drawerContentWidth)
            .heightIn(min = drawerHeight, max = drawerHeight)
            .borderEnd(
                MaterialTheme.colors.onSurface.copy(alpha = borderAlpha),
                with (LocalDensity.current) { 1.dp.toPx() },
            ),
        color = drawerBackgroundColor,
        contentColor = drawerContentColor,
        elevation = elevation,
    ) {
        drawerContent()
    }
}

private fun Modifier.borderEnd(color: Color, width: Float) = drawBehind {
    drawLine(
        color,
        start = Offset(size.width, 0F),
        end = Offset(size.width, size.height),
        strokeWidth = width,
    )
}

@Composable
private fun ModalScrim(
    open: Boolean,
    color: Color,
    onDismiss: () -> Unit,
) {
    if (color.isUnspecified) return

    val alpha by animateFloatAsState(
        targetValue = if (open) 1F else 0F,
        animationSpec = TweenSpec(),
    )

    val closeDrawer = getString(Strings.CloseDrawer)
    val dismissModifier =
        if (open)
            Modifier
                .pointerInput(onDismiss) {
                    detectTapGestures { onDismiss() }
                }
                .semantics {
                    contentDescription = closeDrawer
                    onClick { onDismiss(); true }
                }
        else
            Modifier

    Canvas(
        Modifier.fillMaxSize().then(dismissModifier)
    ) {
        drawRect(color = color, alpha = alpha)
    }
}
