@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.molecules.textarea

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SwitchableTextArea(
    text: String?,
    onTextChanged: (String?) -> Unit = {},
    onSaveClick: (String?) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    var editable by remember { mutableStateOf(false) }
    var inputText by remember(text) { mutableStateOf(text) }

    Card(
        elevation = 0.dp,
        modifier = modifier.border(
            width = 1.dp,
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.12F),
            shape = RoundedCornerShape(4.dp),
        ),
    ) {
        Column {
            Text(
                "メモ",
                style = MaterialTheme.typography.caption,
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp),
            )
            TextAreaContent(
                editable,
                inputText,
                modifier = Modifier.padding(horizontal = 16.dp),
            ) { s ->
                inputText = s
                onTextChanged(s)
            }
            TextAreaButtons(
                editable,
                onPositiveClick = { b ->
                    if (!b) {
                        onSaveClick(inputText?.takeIf(String::isNotBlank))
                    }

                    editable = b
                },
                onNegativeClick = {
                    inputText = text
                    editable = false
                },
                modifier = Modifier.padding(8.dp),
            )
        }
    }
}

@Composable
private fun TextAreaContent(
    editable: Boolean,
    text: String?,
    modifier: Modifier = Modifier,
    onTextChanged: (String?) -> Unit,
) {
    if (!editable) {
        Text(
            text ?: "",
            style = MaterialTheme.typography.body1,
            modifier = modifier,
        )
        return
    }

    Box(modifier) {
        if (text == null) {
            Text(
                "メモを入力",
                style = MaterialTheme.typography.button,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6F),
            )
        }

        OutlinedTextField(
            text ?: "",
            onValueChange = { onTextChanged(it.takeIf(String::isNotBlank)) },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun ColumnScope.TextAreaButtons(
    editable: Boolean,
    onPositiveClick: (Boolean) -> Unit,
    onNegativeClick: () -> Unit,
    modifier: Modifier = Modifier,
) = Row(modifier.align(Alignment.End)) {
    if (editable) {
        TextButton(
            onClick = onNegativeClick,
            modifier = Modifier.padding(end = 8.dp),
        ) { Text("キャンセル") }
    }
    TextButton(
        onClick = { onPositiveClick(!editable) },
    ) { Text(if (editable) "保存" else "編集") }
}
