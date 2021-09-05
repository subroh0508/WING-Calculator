@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import net.subroh0508.wingcalculator.composeui.components.molecules.dialog.FormDialog
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.composeui.pages.simple.provideCreatePresetDispatcher
import net.subroh0508.wingcalculator.composeui.pages.simple.provideUpdatePresetDispatcher

@Composable
fun SimplePresetCreateDialog(
    onClose: () -> Unit,
) {
    val (uiModel, createUseCase) = provideCreatePresetDispatcher()

    SimplePresetSaveDialog(uiModel.form, onClose, createUseCase)
}

@Composable
fun SimplePresetUpdateDialog(
    onClose: () -> Unit,
) {
    val (uiModel, updateUseCase) = provideUpdatePresetDispatcher()

    SimplePresetSaveDialog(uiModel.form, onClose, updateUseCase)
}

@Composable
private fun SimplePresetSaveDialog(
    form: SimpleCalculatorUiModel.Form,
    onClose: () -> Unit,
    onClickSave: (String) -> Unit,
) {
    var presetName by remember(form.name) { mutableStateOf(form.name ?: "") }

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
            presetName,
            onValueChange = { presetName = it },
        )
    }
}
