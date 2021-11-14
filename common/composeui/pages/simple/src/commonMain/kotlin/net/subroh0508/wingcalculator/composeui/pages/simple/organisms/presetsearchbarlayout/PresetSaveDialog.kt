@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms.presetsearchbarlayout

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import net.subroh0508.wingcalculator.composeui.components.CommonStrings
import net.subroh0508.wingcalculator.composeui.components.molecules.dialog.FormDialog
import net.subroh0508.wingcalculator.composeui.components.getString as getCommonString
import net.subroh0508.wingcalculator.composeui.pages.simple.Strings
import net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers.provideCreatePresetDispatcher
import net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers.provideInputFormDispatcher
import net.subroh0508.wingcalculator.composeui.pages.simple.getString

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
    val (uiModel, dispatch) = provideInputFormDispatcher()

    SimplePresetSaveDialog(uiModel.form.name, onClose) { dispatch(name = it) }
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
        title = { Text(getString(Strings.PresetSaveDialogTitle)) },
        confirmButton = {
            TextButton(onClick = {
                onClickSave(presetName)
                onClose()
            }) {
                Text(getCommonString(CommonStrings.Save))
            }
        },
        dismissButton = {
            TextButton(onClick = onClose) {
                Text(getCommonString(CommonStrings.Close))
            }
        },
    ) {
        OutlinedTextField(
            presetName ?: "",
            onValueChange = { presetName = it },
        )
    }
}
