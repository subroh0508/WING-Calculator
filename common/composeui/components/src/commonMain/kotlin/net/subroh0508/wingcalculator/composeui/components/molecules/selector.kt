@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.molecules

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import net.subroh0508.wingcalculator.composeui.components.atoms.NumberField
import net.subroh0508.wingcalculator.composeui.components.imports.DropdownMenu
import net.subroh0508.wingcalculator.composeui.components.imports.DropdownMenuItem

private val WEEK_REGEX = """^[1-8]?$""".toRegex()

@Composable
fun <E: Enum<*>> WeekSelector(
    selectedSeason: E,
    week: Int,
    seasons: Array<E>,
    isWeekNumberEnabled: Boolean,
    onChange: (E, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selection by remember { mutableStateOf(WeekSelection(selectedSeason, week))}

    LaunchedEffect(selection) { onChange(selection.season, selection.week) }

    Column(modifier = modifier) {
        Text(
            "シーズン・週",
            style = MaterialTheme.typography.h6,
        )
        Row {
            DropdownSelector(
                selectedSeason.toString(),
                seasons.map(Enum<*>::toString),
                onClick = { selection = selection.copy(season = seasons[it]) },
                labelWidth = 92.dp,
            )
            if (isWeekNumberEnabled) {
                Spacer(modifier = Modifier.width(16.dp))
                DropdownSelector(
                    "${selection.week}週目",
                    WEEK_RANGE.map { "${it}週目" },
                    onClick = { selection = selection.copy(week = it + 1) },
                    labelWidth = 72.dp,
                )
            }
        }
    }
}

private data class WeekSelection<out T: Enum<*>>(
    val season: T,
    val week: Int = 1,
)

private val WEEK_RANGE = 1..8

@Composable
fun AppealRatioSelector(
    selectedRatio: String,
    onChange: (Double) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            "アピール倍率",
            style = MaterialTheme.typography.h6,
        )
        DropdownSelector(
            selectedRatio,
            APPEAL_RATIO_RANGE.map { "%.1f倍".format(it * 0.1) },
            onClick = { onChange(APPEAL_RATIO_RANGE.toList()[it] * 0.1) },
            labelWidth = 64.dp
        )
    }
}

private val APPEAL_RATIO_RANGE = 10..50

@Composable
private fun DropdownSelector(
    selectedItem: String,
    items: List<String>,
    onClick: (Int) -> Unit,
    labelWidth: Dp,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Row {
            Text(
                selectedItem,
                modifier = Modifier.width(labelWidth)
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically),
            )
            IconButton(onClick = { expanded = true }) {
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = "Select",
                )
            }
        }

        DropdownMenu(
            expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.requiredSizeIn(maxHeight = 240.dp),
        ) {
            items.forEachIndexed { i, item ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onClick(i)
                    },
                    modifier = Modifier.width(labelWidth + 48.dp),
                ) {
                    Text(item)
                }
            }
        }
    }
}
