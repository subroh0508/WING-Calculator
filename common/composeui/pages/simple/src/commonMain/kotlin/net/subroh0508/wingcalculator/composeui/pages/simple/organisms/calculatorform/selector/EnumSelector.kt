@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculatorform.selector

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.appeal.model.buffform.AppealJudge
import net.subroh0508.wingcalculator.appeal.model.buffform.MemoryLevel
import net.subroh0508.wingcalculator.appeal.model.buffform.Week
import net.subroh0508.wingcalculator.composeui.components.molecules.selector.DropdownSelector
import net.subroh0508.wingcalculator.utilities.extensions.toFixed

@Composable
fun WeekSelector(
    selected: Week.Season,
    week: Int,
    onChange: (Week.Season, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selection by remember { mutableStateOf(WeekSelection(selected, week)) }

    LaunchedEffect(selection) { onChange(selection.season, selection.week) }

    val isWeekNumberEnabled = !listOf(Week.Season.SEMI_FINAL, Week.Season.FINAL).contains(selected)

    Column(modifier = modifier) {
        Text(
            "シーズン・週",
            style = MaterialTheme.typography.subtitle1,
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            DropdownSelector(
                selected.toString(),
                Week.Season.values().map(Enum<*>::toString),
                onClick = { selection = selection.copy(season = Week.Season.values()[it]) },
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

private data class WeekSelection(
    val season: Week.Season,
    val week: Int = 1,
)

private val WEEK_RANGE = 1..8

@Composable
fun AppealRatioSelector(
    selectedRatio: String,
    onChange: (Double) -> Unit,
    modifier: Modifier = Modifier,
) = StringListSelector(
    "アピール倍率",
    selectedRatio,
    APPEAL_RATIO_RANGE.map { "${(it * 0.1).toFixed(1)}倍" },
    onChange = { onChange(APPEAL_RATIO_RANGE.toList()[it] * 0.1) },
    modifier = modifier,
)

private val APPEAL_RATIO_RANGE = 10..50

@Composable
fun AppealJudgeSelector(
    selected: AppealJudge.Factor,
    onChange: (AppealJudge.Factor) -> Unit,
    modifier: Modifier = Modifier,
) = EnumSelector("アピール判定", selected, AppealJudge.Factor.values(), onChange, modifier)

@Composable
fun MemoryLevelSelector(
    selected: MemoryLevel,
    onChange: (MemoryLevel) -> Unit,
    modifier: Modifier = Modifier,
) = EnumSelector("思い出Lv", selected, MemoryLevel.values(), onChange, modifier)

@Composable
private fun <E: Enum<*>> EnumSelector(
    label: String,
    selected: E,
    items: Array<E>,
    onChange: (E) -> Unit,
    modifier: Modifier = Modifier,
) = StringListSelector(
    label,
    selected.toString(),
    items.map(Enum<*>::toString),
    onChange = { onChange(items[it]) },
    modifier = modifier,
)

@Composable
private fun StringListSelector(
    label: String,
    selected: String,
    items: List<String>,
    onChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) = Column(modifier = modifier) {
    Text(label, style = MaterialTheme.typography.subtitle1)
    Row {
        DropdownSelector(selected, items, onClick = onChange)
    }
}
