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
import java.time.format.TextStyle

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
            style = MaterialTheme.typography.subtitle1,
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            DropdownSelector(
                selectedSeason.toString(),
                seasons.map(Enum<*>::toString),
                onClick = { selection = selection.copy(season = seasons[it]) },
                modifier = Modifier.weight(1F),
            )
            Spacer(modifier = Modifier.width(8.dp))
            if (isWeekNumberEnabled) {
                DropdownSelector(
                    "${selection.week}週目",
                    WEEK_RANGE.map { "${it}週目" },
                    onClick = { selection = selection.copy(week = it + 1) },
                    modifier = Modifier.weight(1F),
                )
            } else {
                Spacer(modifier = Modifier.weight(1F))
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
            style = MaterialTheme.typography.subtitle1,
        )
        DropdownSelector(
            selectedRatio,
            APPEAL_RATIO_RANGE.map { "%.1f倍".format(it * 0.1) },
            onClick = { onChange(APPEAL_RATIO_RANGE.toList()[it] * 0.1) },
        )
    }
}

private val APPEAL_RATIO_RANGE = 10..50

@Composable
fun BuffRatioField(
    buffRatio: String,
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
            label = "バフ補正",
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
fun <E: Enum<*>> AppealJudgeSelector(
    selectedFactor: E,
    factors: Array<E>,
    onChange: (E) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            "アピール判定",
            style = MaterialTheme.typography.subtitle1,
        )
        Row {
            DropdownSelector(
                selectedFactor.toString(),
                factors.map(Enum<*>::toString),
                onClick = { onChange(factors[it]) },
            )
        }
    }
}

@Composable
fun InterestRatioField(
    interestRatio: String,
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
            label = "興味度",
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
private fun DropdownSelector(
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
