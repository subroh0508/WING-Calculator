@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.molecules

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

interface SwitcherLabel {
    val text: String
}

@Composable
fun <T: SwitcherLabel> ColumnScope.Switcher(
    type: T,
    onClickBack: () -> Unit,
    onClickForward: () -> Unit,
) = Row(
    modifier = Modifier.padding(vertical = 8.dp)
        .align(Alignment.CenterHorizontally),
) {
    IconButton(
        onClick = onClickBack
    ) {
        Icon(
            Icons.Default.KeyboardArrowLeft,
            contentDescription = "back",
            modifier = Modifier.size(24.dp),
        )
    }
    Text(
        type.text,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.padding(horizontal = 16.dp)
            .align(Alignment.CenterVertically),
    )
    IconButton(
        onClick = onClickForward
    ) {
        Icon(
            Icons.Default.KeyboardArrowRight,
            contentDescription = "forward",
            modifier = Modifier.size(24.dp),
        )
    }
}

