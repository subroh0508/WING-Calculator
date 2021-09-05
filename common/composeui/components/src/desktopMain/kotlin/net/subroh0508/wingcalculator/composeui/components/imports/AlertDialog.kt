@file:Suppress("FunctionName")
@file:JvmName("DesktopAlertDialog")

package net.subroh0508.wingcalculator.composeui.components.imports

import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

@Composable
internal actual fun ExpectAlertDialog(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    modifier: Modifier,
    dismissButton: @Composable (() -> Unit)?,
    title: @Composable (() -> Unit)?,
    text: @Composable (() -> Unit)?,
    shape: Shape,
    backgroundColor: Color,
    contentColor: Color,
) = androidx.compose.material.AlertDialog(
    onDismissRequest,
    confirmButton,
    modifier,
    dismissButton,
    title,
    text,
    shape,
    backgroundColor,
    contentColor,
)

@Composable
internal actual fun ExpectAlertDialog(
    onDismissRequest: () -> Unit,
    buttons: @Composable () -> Unit,
    modifier: Modifier,
    title: @Composable (() -> Unit)?,
    text: @Composable (() -> Unit)?,
    shape: Shape,
    backgroundColor: Color,
    contentColor: Color,
) = androidx.compose.material.AlertDialog(
    onDismissRequest,
    buttons,
    modifier,
    title,
    text,
    shape,
    backgroundColor,
    contentColor,
)
