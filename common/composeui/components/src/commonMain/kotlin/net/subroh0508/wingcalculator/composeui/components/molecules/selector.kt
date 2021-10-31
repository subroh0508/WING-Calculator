@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.molecules

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
import net.subroh0508.wingcalculator.composeui.components.atoms.NumberField
import net.subroh0508.wingcalculator.composeui.components.imports.DropdownMenu
import net.subroh0508.wingcalculator.composeui.components.imports.DropdownMenuItem

@Composable
fun BuffRatioField(
    buffRatio: String,
    total: String,
    onChange: (List<Double>) -> Unit,
    modifier: Modifier = Modifier,
) {
    var ratio by remember { mutableStateOf(buffRatio) }

    LaunchedEffect(ratio) {
        val array = ratio.split(",")
            .filter(String::isNotBlank)
            .mapNotNull { it.toIntOrNull()?.let { n -> n * 0.01 } }

        if (array.isNotEmpty()) onChange(array)
    }

    Column(modifier = modifier) {
        NumberField(
            ratio,
            label = "バフ補正: $total",
            backgroundColor = MaterialTheme.colors.surface,
            onChangeValue = { ratio = it },
            regex = BUFF_RATIO_REGEX,
            modifier = Modifier.fillMaxWidth(),
        )
        Text(
            "カンマ区切り(単位: %)",
            style = MaterialTheme.typography.caption,
            modifier = Modifier.height(16.dp)
                .padding(start = 16.dp),
        )
    }
}

private val BUFF_RATIO_REGEX = """^((0|-?[1-9]?[0-9]?|-?100),?)*$""".toRegex()

@Composable
fun InterestRatioField(
    interestRatio: String,
    total: String,
    onChange: (List<Double>) -> Unit,
    modifier: Modifier = Modifier,
) {
    var ratio by remember { mutableStateOf(interestRatio) }

    LaunchedEffect(ratio) {
        val array = ratio.split(",")
            .filter(String::isNotBlank)
            .mapNotNull { it.toDoubleOrNull()?.takeIf { r -> r > 0.0 } }

        if (array.isNotEmpty()) onChange(array)
    }

    Column(modifier = modifier) {
        NumberField(
            ratio,
            label = "興味度: $total",
            backgroundColor = MaterialTheme.colors.surface,
            onChangeValue = { ratio = it },
            regex = INTEREST_RATIO_REGEX,
            modifier = Modifier.fillMaxWidth(),
        )
        Text(
            "カンマ区切り",
            style = MaterialTheme.typography.caption,
            modifier = Modifier.height(16.dp)
                .padding(start = 16.dp),
        )
    }
}

private val INTEREST_RATIO_REGEX = """^([0-1]?\.?[0-9]{0,2},?)*$""".toRegex()

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
