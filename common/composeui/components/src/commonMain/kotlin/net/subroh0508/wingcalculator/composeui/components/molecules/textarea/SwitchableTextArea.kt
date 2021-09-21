@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.molecules.textarea

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
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

    if (editable) {
        OutlinedTextField(
            inputText ?: "",
            { s ->
                inputText = s.takeIf(String::isNotBlank)
                onTextChanged(s.takeIf(String::isNotBlank))
            },
            label = { Text("メモ") },
            placeholder = { Text("メモを入力") },
            trailingIcon = {
                IconButton(
                    onClick = {
                        editable = false
                        onSaveClick(inputText?.takeIf(String::isNotBlank))
                    }
                ) { Icon(Icons.Default.Save, contentDescription = "Save") }
            },
            modifier = modifier,
        )
        return
    }

    Card(
        elevation = 0.dp,
        modifier = modifier.border(
            width = 1.dp,
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.12F),
            shape = RoundedCornerShape(4.dp),
        ),
    ) {
        Column {
            Row(Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)) {
                Text(
                    "メモ",
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.weight(1F),
                )
            }

            Text(
                text ?: "",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
            TextButton(
                onClick = { editable = true },
                modifier = Modifier.padding(8.dp)
                    .align(Alignment.End),
            ) { Text("編集") }
        }
    }
}
