@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.molecules.selector

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.imports.DropdownMenu
import net.subroh0508.wingcalculator.composeui.components.imports.DropdownMenuItem

@Composable
fun DropdownSelector(
    selectedItem: String,
    items: List<String>,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    var buttonSize by remember { mutableStateOf(IntSize.Zero) }

    Box(
        modifier = modifier.onGloballyPositioned {
            buttonSize = it.size
        },
    ) {
        OutlinedButton(
            onClick = { expanded = true },
            contentPadding = PaddingValues(0.dp, 8.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colors.onSurface,
            ),
        ) {
            Text(
                selectedItem,
                modifier = Modifier.weight(1F)
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterVertically),
            )
            Icon(
                Icons.Default.ArrowDropDown,
                contentDescription = "Select",
                modifier = Modifier.size(24.dp),
            )
            Spacer(modifier = Modifier.width(12.dp))
        }

        with (LocalDensity.current) {
            DropdownMenu(
                expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(buttonSize.width.toDp())
                    .requiredSizeIn(maxHeight = 240.dp),
            ) {
                items.forEachIndexed { i, item ->
                    DropdownMenuItem(
                        onClick = {
                            expanded = false
                            onClick(i)
                        },
                    ) {
                        Text(item)
                    }
                }
            }
        }
    }
}
