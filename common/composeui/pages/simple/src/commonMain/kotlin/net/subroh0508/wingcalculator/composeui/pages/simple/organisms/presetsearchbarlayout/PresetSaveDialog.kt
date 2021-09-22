@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms.presetsearchbarlayout

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import net.subroh0508.wingcalculator.composeui.components.molecules.dialog.FormDialog
import net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers.provideCreatePresetDispatcher
import net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers.provideUpdatePresetDispatcher

@Composable
fun SimplePresetCreateDialog(
    onClose: () -> Unit,
) {
    val (_, dispatch) = provideCreatePresetDispatcher()

    SimplePresetSaveDialog(null, onClose, dispatch)
}

@Composable
fun SimplePresetUpdateDialog(
    onClose: () -> Unit,
) {
    val (uiModel, dispatch) = provideUpdatePresetDispatcher()

    SimplePresetSaveDialog(uiModel.form.name, onClose, dispatch)
}

@Composable
private fun SimplePresetSaveDialog(
    name: String?,
    onClose: () -> Unit,
    onClickSave: (String?) -> Unit,
) {
    var presetName by remember(name) { mutableStateOf(name) }

    FormDialog(
        onDismissRequest = onClose,
        title = { Text("プリセットを保存") },
        confirmButton = {
            TextButton(onClick = {
                onClickSave(presetName)
                onClose()
            }) {
                Text("保存")
            }
        },
        dismissButton = {
            TextButton(onClick = onClose) {
                Text("閉じる")
            }
        },
    ) {
        OutlinedTextField(
            presetName ?: "",
            onValueChange = { presetName = it },
        )
    }
}
