@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.molecules.AppealJudgeSelector
import net.subroh0508.wingcalculator.composeui.components.molecules.AppealRatioSelector
import net.subroh0508.wingcalculator.composeui.components.molecules.BuffRatioField
import net.subroh0508.wingcalculator.composeui.components.molecules.WeekSelector
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorDispatcherContext
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.data.AppealJudge
import net.subroh0508.wingcalculator.data.AppealRatio
import net.subroh0508.wingcalculator.data.Buff
import net.subroh0508.wingcalculator.data.Week

@Composable
fun SimpleBuffForm() {
    val uiModel = SimpleCalculatorProviderContext.current
    val onChangeUiModel = SimpleCalculatorDispatcherContext.current

    val (_, _, week, appealRatio, buff, appealJudge) = uiModel

    Column {
        WeekSelector(
            week.season,
            week.week,
            Week.Season.values(),
            !listOf(Week.Season.SEMI_FINAL, Week.Season.FINAL).contains(week.season),
            onChange = { season, week -> onChangeUiModel(uiModel.copy(week = Week(season, week))) },
        )
        Spacer(Modifier.height(16.dp))
        AppealRatioSelector(
            appealRatio.toString(),
            onChange = { ratio -> onChangeUiModel(uiModel.copy(appealRatio = AppealRatio(ratio))) },
        )
        Spacer(Modifier.height(16.dp))
        BuffRatioField(
            buff.toString(),
            onChange = { ratio -> onChangeUiModel(uiModel.copy(buff = Buff(ratio))) },
        )
        Spacer(Modifier.height(16.dp))
        AppealJudgeSelector(
            appealJudge.factor,
            AppealJudge.Factor.values(),
            onChange = { factor -> onChangeUiModel(uiModel.copy(appealJudge = AppealJudge(factor))) },
        )
    }
}
