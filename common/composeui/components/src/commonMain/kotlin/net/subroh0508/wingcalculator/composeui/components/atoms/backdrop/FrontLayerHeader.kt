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
import net.subroh0508.wingcalculator.composeui.components.Strings
import net.subroh0508.wingcalculator.composeui.components.getString

@Composable
fun FrontLayerHeader(
    label: String,
    headerHeight: Dp,
    isConcealed: Boolean? = null,
    onClickArrowIcon: () -> Unit = {},
    actions: @Composable () -> Unit = {},
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
    actions()
    if (isConcealed != null) {
        IconButton(onClick = onClickArrowIcon) {
            val (icon, description) =
                if (isConcealed)
                    Icons.Default.KeyboardArrowUp to getString(Strings.IconDescriptionBackdropReveal)
                else
                    Icons.Default.KeyboardArrowDown to getString(Strings.IconDescriptionBackdropConceal)

            Icon(icon, contentDescription = description)
        }
    }
}
