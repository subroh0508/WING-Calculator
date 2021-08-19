@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.molecules.*
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorDispatcherContext
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.data.*

@Composable
fun SimpleBuffForm(modifier: Modifier = Modifier) {
    val uiModel = SimpleCalculatorProviderContext.current
    val onChangeUiModel = SimpleCalculatorDispatcherContext.current

    val (_, _, week, appealRatio, buff, appealJudge, interestRatio) = uiModel

    Column(modifier = modifier.padding(horizontal = 8.dp)) {
        WeekSelector(
            week.season,
            week.week,
            Week.Season.values(),
            !listOf(Week.Season.SEMI_FINAL, Week.Season.FINAL).contains(week.season),
            onChange = { season, week -> onChangeUiModel(uiModel.copy(week = Week(season, week))) },
        )
        Spacer(Modifier.height(16.dp))
        Row {
            AppealRatioSelector(
                appealRatio.toString(),
                onChange = { ratio -> onChangeUiModel(uiModel.copy(appealRatio = AppealRatio(ratio))) },
                modifier = Modifier.weight(1F),
            )
            Spacer(Modifier.width(8.dp))
            AppealJudgeSelector(
                appealJudge.factor,
                AppealJudge.Factor.values(),
                onChange = { factor -> onChangeUiModel(uiModel.copy(appealJudge = AppealJudge(factor))) },
                modifier = Modifier.weight(1F),
            )
        }
        Spacer(Modifier.height(16.dp))
        Row {
            BuffRatioField(
                buff.toString(),
                onChange = { ratio -> onChangeUiModel(uiModel.copy(buff = Buff(ratio))) },
                modifier = Modifier.weight(1F),
            )
            Spacer(Modifier.width(8.dp))
            InterestRatioField(
                interestRatio.toString(),
                onChange = { ratio -> onChangeUiModel(uiModel.copy(interestRatio = InterestRatio(ratio))) },
                modifier = Modifier.weight(1F),
            )
        }
    }
}
