@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.molecules.menu

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.imports.DropdownMenu
import net.subroh0508.wingcalculator.composeui.components.imports.DropdownMenuItem

interface DropdownMenuItem {
    val label: String
}

@Composable
fun <T: DropdownMenuItem> ExpandableDropdownMenu(
    items: List<T>,
    onClick: (T) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expanded = true },
        modifier = Modifier.padding(4.dp),
    ) {
        Icon(
            Icons.Default.MoreVert,
            contentDescription = "menu_save",
            modifier = Modifier.size(24.dp),
        )

        DropdownMenu(
            expanded,
            onDismissRequest = { expanded = false },
        ) {
            items.forEachIndexed { _, item ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onClick(item)
                    },
                ) { Text(item.label) }
            }
        }
    }
}
