@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.molecules.menu

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.imports.DropdownMenu
import net.subroh0508.wingcalculator.composeui.components.imports.DropdownMenuItem

interface DropdownMenuItem {
    @get:Composable
    val label: String
}

@Composable
fun <T: DropdownMenuItem> ExpandableDropdownMenu(
    items: List<T>,
    onClick: (T) -> Unit,
    selected: T? = null,
    icon: @Composable () -> Unit = {
        Icon(
            Icons.Default.MoreVert,
            contentDescription = "MenuSave",
            modifier = Modifier.size(24.dp),
        )
    },
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expanded = true },
        modifier = Modifier.padding(4.dp),
    ) {
        icon()

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
                ) {
                    when {
                        selected == null -> Unit
                        selected == item -> Icon(
                            Icons.Default.Check,
                            contentDescription = "Selected",
                            modifier = CheckIconSize,
                        )
                        selected != item -> Spacer(CheckIconSize)
                    }

                    Text(
                        item.label,
                        modifier = if (selected == null)
                                       Modifier
                                   else
                                       Modifier.padding(20.dp)
                    )
                }
            }
        }
    }
}

private val CheckIconSize = Modifier.size(24.dp)
