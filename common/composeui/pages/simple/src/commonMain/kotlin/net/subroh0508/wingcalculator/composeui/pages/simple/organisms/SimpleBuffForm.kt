@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import net.subroh0508.wingcalculator.composeui.components.molecules.AppealRatioSelector
import net.subroh0508.wingcalculator.composeui.components.molecules.WeekSelector
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorDispatcherContext
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.data.AppealRatio
import net.subroh0508.wingcalculator.data.Week

@Composable
fun SimpleBuffForm() {
    val uiModel = SimpleCalculatorProviderContext.current
    val onChangeUiModel = SimpleCalculatorDispatcherContext.current

    val (_, _, week, appealRatio) = uiModel

    Column {
        WeekSelector(
            week.season,
            week.week,
            Week.Season.values(),
            !listOf(Week.Season.SEMI_FINAL, Week.Season.FINAL).contains(week.season),
            onChange = { season, week -> onChangeUiModel(uiModel.copy(week = Week(season, week))) },
        )

        AppealRatioSelector(
            appealRatio.toString(),
            onChange = { ratio -> onChangeUiModel(uiModel.copy(appealRatio = AppealRatio(ratio))) },
        )
    }
}
