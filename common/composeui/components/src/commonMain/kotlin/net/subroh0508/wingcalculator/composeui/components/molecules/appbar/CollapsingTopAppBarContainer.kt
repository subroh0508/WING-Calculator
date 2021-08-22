@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.molecules.appbar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun CollapsingTopAppBarContainer(
    onClickAppBarNavigation: () -> Unit,
    title: String? = null,
    appBarBackgroundColor: Color = MaterialTheme.colors.primarySurface,
    appBarElevation: Dp = AppBarDefaults.TopAppBarElevation,
    appBarActions: @Composable RowScope.() -> Unit = {},
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    val appBarHeight = 56.dp

    val appBarHeightPx = with(LocalDensity.current) { appBarHeight.roundToPx().toFloat() }
    val appBarOffsetHeightPx = remember { mutableStateOf(0f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                println("onPreScroll: $available")
                val delta = available.y
                val newOffset = appBarOffsetHeightPx.value + delta
                appBarOffsetHeightPx.value = newOffset.coerceIn(-appBarHeightPx, 0f)
                return Offset.Zero
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                println("onPostScroll: $available")
                return Offset.Zero
            }
        }
    }

    val verticalScrollState = rememberScrollState(0)

    Box(
        modifier = modifier.fillMaxSize()
            .nestedScroll(nestedScrollConnection),
    ) {
        Column(modifier = Modifier.verticalScroll(verticalScrollState)) {
            Spacer(modifier = Modifier.height(appBarHeight))
            content()
        }

        TopAppBar(
            onClickAppBarNavigation,
            title,
            appBarBackgroundColor,
            appBarElevation,
            actions = appBarActions,
            modifier = Modifier.height(appBarHeight)
                .offset { IntOffset(x = 0, y = appBarOffsetHeightPx.value.roundToInt()) }
        )
    }
}
