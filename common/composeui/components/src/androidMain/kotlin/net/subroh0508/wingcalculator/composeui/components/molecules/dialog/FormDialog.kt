@file:Suppress("FunctionName")
@file:JvmName("AndroidFormDialog")

package net.subroh0508.wingcalculator.composeui.components.molecules.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
internal actual fun ExpectFormDialog(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    modifier: Modifier,
    dismissButton: @Composable (() -> Unit)?,
    title: @Composable (() -> Unit)?,
    shape: Shape,
    backgroundColor: Color,
    contentColor: Color,
    content: @Composable () -> Unit,
) = Dialog(
    onDismissRequest = onDismissRequest,
    properties = DialogProperties(),
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = backgroundColor,
        contentColor = contentColor,
    ) {
        Column(modifier = Modifier.width(IntrinsicSize.Max)) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 24.dp),
            ) {
                Spacer(Modifier.height(24.dp))
                title?.let { FormDialogTitle(it) }
                Spacer(Modifier.height(24.dp))
                content()
            }

            Spacer(Modifier.height(28.dp))
            FormDialogButtons(confirmButton, dismissButton)
        }
    }
}
