@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.atoms.backdrop

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FrontLayerHeader(
    label: String,
    headerHeight: Dp,
    isConcealed: Boolean? = null,
    onClickIcon: () -> Unit = {},
    modifier: Modifier = Modifier,
) = Row(
    modifier = modifier.height(headerHeight)
        .padding(start = 16.dp),
) {
    Text(
        label,
        modifier = Modifier.weight(1F)
            .padding(vertical = 8.dp)
            .align(Alignment.CenterVertically),
    )
    if (isConcealed != null) {
        IconButton(onClick = onClickIcon) {
            val (icon, description) =
                if (isConcealed)
                    Icons.Default.KeyboardArrowUp to "reveal"
                else
                    Icons.Default.KeyboardArrowDown to "conceal"

            Icon(icon, contentDescription = description)
        }
    }
}
