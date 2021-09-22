@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.molecules.appbar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

private val DefaultAppBarHeight = 56.dp

@Composable
fun CollapsingTopAppBarLayout(
    appBar: @Composable (Modifier) -> Unit,
    appBarHeight: Dp = DefaultAppBarHeight,
    isCollapsingEnable: Boolean = true,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    val appBarHeightPx = with(LocalDensity.current) { appBarHeight.roundToPx().toFloat() }
    val appBarOffsetHeightPx = remember { mutableStateOf(0f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = appBarOffsetHeightPx.value + delta
                appBarOffsetHeightPx.value = newOffset.coerceIn(-appBarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }

    val verticalScrollState = rememberScrollState(0)
    var boxModifier = modifier.fillMaxSize()
    if (isCollapsingEnable) {
        boxModifier = boxModifier.nestedScroll(nestedScrollConnection)
    }
    val columnModifier = if (isCollapsingEnable) Modifier.verticalScroll(verticalScrollState) else Modifier

    Box(modifier = boxModifier) {
        Column(modifier = columnModifier) {
            Spacer(modifier = Modifier.height(appBarHeight))
            content()
        }

        appBar(
            Modifier.height(appBarHeight)
                .offset { IntOffset(x = 0, y = appBarOffsetHeightPx.value.roundToInt()) }
        )
    }
}
