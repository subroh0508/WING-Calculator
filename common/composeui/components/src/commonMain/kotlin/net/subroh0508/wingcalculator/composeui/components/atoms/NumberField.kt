@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.atoms

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun NumberField(
    n: String,
    onChangeValue: (String) -> Unit,
    label: String? = null,
    enabled: Boolean = true,
    focusedColor: Color = MaterialTheme.colors.primary.copy(alpha = ContentAlpha.high),
    regex: Regex? = null,
    modifier: Modifier = Modifier,
) = OutlinedTextField(
    n,
    { s -> if (regex == null || s.matches(regex)) onChangeValue(s) },
    label = label?.let { { Text(it) } },
    enabled = enabled,
    singleLine = true,
    modifier = modifier,
    colors = TextFieldDefaults.textFieldColors(
        focusedIndicatorColor = focusedColor,
        focusedLabelColor = focusedColor,
    ),
)