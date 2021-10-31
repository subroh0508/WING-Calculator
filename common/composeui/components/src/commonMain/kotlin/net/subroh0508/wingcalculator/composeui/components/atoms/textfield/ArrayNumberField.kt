@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.atoms.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ArrayNumberField(
    text: String,
    label: String,
    helperText: String,
    onChangeValue: (String) -> Unit,
    regex: Regex,
    modifier: Modifier = Modifier,
) = Column(modifier = modifier) {
    NumberField(
        text,
        label = label,
        backgroundColor = MaterialTheme.colors.surface,
        onChangeValue = onChangeValue,
        regex = regex,
        modifier = Modifier.fillMaxWidth(),
    )
    Text(
        helperText,
        style = MaterialTheme.typography.caption,
        modifier = Modifier.height(16.dp)
            .padding(start = 16.dp),
    )
}
