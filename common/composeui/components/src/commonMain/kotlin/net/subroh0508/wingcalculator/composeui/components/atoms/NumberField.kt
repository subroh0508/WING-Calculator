@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.atoms

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val NUMBER_REGEX = """^(0|[1-9][0-9]*)$""".toRegex()

@Composable
fun NumberField(
    n: String,
    onChangeValue: (String) -> Unit,
    focusedColor: Color = MaterialTheme.colors.primary.copy(alpha = ContentAlpha.high),
    label: @Composable (() -> Unit)? = null,
) = OutlinedTextField(
    n,
    { s -> if (s.matches(NUMBER_REGEX)) onChangeValue(s) },
    modifier = Modifier.padding(8.dp),
    colors = TextFieldDefaults.textFieldColors(
        focusedIndicatorColor = focusedColor,
        focusedLabelColor = focusedColor,
    ),
    label = label,
)
