@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.atoms.list

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.subroh0508.wingcalculator.composeui.components.getString
import net.subroh0508.wingcalculator.composeui.components.Strings

@Composable
fun DeletableListItem(
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: @Composable () -> Unit,
) = ListItem(
    trailing = {
        IconButton(onClick = onDeleteClick) {
            Icon(
                Icons.Default.Delete,
                contentDescription = getString(Strings.IconDescriptionDeletableListItem),
                tint = LocalContentColor.current.copy(alpha = 0.6F),
            )
        }
    },
    text = text,
    modifier = modifier,
)
