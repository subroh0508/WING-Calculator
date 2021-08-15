@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.molecules

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import net.subroh0508.wingcalculator.composeui.components.atoms.NumberField
import net.subroh0508.wingcalculator.composeui.components.imports.DropdownMenu
import net.subroh0508.wingcalculator.composeui.components.imports.DropdownMenuItem

private val WEEK_REGEX = """^[1-8]*$""".toRegex()

@Composable
fun <E: Enum<*>> WeekSelector(
    selectedSeason: E,
    week: Int,
    seasons: Array<E>,
    isWeekNumberVisible: Boolean,
    onChange: (E, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selection by remember { mutableStateOf(WeekSelection(selectedSeason, week.toString()))}

    LaunchedEffect(selection) {
        val intWeek = if (selection.week.isBlank()) 1 else selection.week.toIntOrNull()

        onChange(selection.season, intWeek ?: week)
    }

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
            )

            if (isWeekNumberVisible) {
                NumberField(
                    selection.week,
                    label = "週",
                    regex = WEEK_REGEX,
                    onChangeValue = { selection = selection.copy(week = it) },
                )
            }
        }
    }
}

private data class WeekSelection<out T: Enum<*>>(
    val season: T,
    val week: String = "",
)

@Composable
private fun DropdownSelector(
    selectedItem: String,
    items: List<String>,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Row {
            Text(selectedItem)
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
        ) {
            items.forEachIndexed { i, item ->
                DropdownMenuItem(onClick = { onClick(i) }) {
                    Text(item)
                }
            }
        }
    }
}
