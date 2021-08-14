@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.atoms

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val NUMBER_REGEX = """^(0|[1-9][0-9]*)*$""".toRegex()

@Composable
fun NumberField(
    n: String,
    onChangeValue: (String) -> Unit,
    label: String,
    focusedColor: Color = MaterialTheme.colors.primary.copy(alpha = ContentAlpha.high),
    modifier: Modifier = Modifier,
) = OutlinedTextField(
    n,
    { s -> if (s.matches(NUMBER_REGEX)) onChangeValue(s) },
    label = { Text(label) },
    singleLine = true,
    modifier = modifier.padding(horizontal = 8.dp),
    colors = TextFieldDefaults.textFieldColors(
        focusedIndicatorColor = focusedColor,
        focusedLabelColor = focusedColor,
    ),
)