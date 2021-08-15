@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.imports

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.MenuDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset

@Composable
internal actual fun ExpectDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier,
    offset: DpOffset,
    content: @Composable ColumnScope.() -> Unit,
) = androidx.compose.material.DropdownMenu(
    expanded,
    onDismissRequest,
    modifier,
    offset,
    content = content,
)

@Composable
internal actual fun ExpectDropdownMenuItem(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    contentPadding: PaddingValues,
    interactionSource: MutableInteractionSource,
    content: @Composable RowScope.() -> Unit,
) = androidx.compose.material.DropdownMenuItem(
    onClick,
    modifier,
    enabled,
    contentPadding,
    interactionSource,
    content,
)
