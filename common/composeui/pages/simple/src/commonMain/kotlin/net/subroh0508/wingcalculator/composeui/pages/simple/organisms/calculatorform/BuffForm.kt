@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculatorform

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.molecules.*
import net.subroh0508.wingcalculator.appeal.model.*
import net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers.provideInputFormDispatcher

@Composable
fun BuffForm(modifier: Modifier = Modifier) {
    val (uiModel, dispatch) = provideInputFormDispatcher()

    val (_, _, week, appealRatio, buff, appealJudge, interestRatio) = uiModel.form

    Column(modifier = modifier.padding(horizontal = 8.dp)) {
        WeekSelector(
            week.season,
            week.week,
            Week.Season.values(),
            !listOf(Week.Season.SEMI_FINAL, Week.Season.FINAL).contains(week.season),
            onChange = { season, week -> dispatch(Week(season, week)) },
        )
        Spacer(Modifier.height(16.dp))
        Row {
            AppealRatioSelector(
                appealRatio.toString(),
                onChange = { ratio -> dispatch(AppealRatio(ratio)) },
                modifier = Modifier.weight(1F),
            )
            Spacer(Modifier.width(8.dp))
            AppealJudgeSelector(
                appealJudge.factor,
                AppealJudge.Factor.values(),
                onChange = { factor -> dispatch(AppealJudge(factor)) },
                modifier = Modifier.weight(1F),
            )
        }
        Spacer(Modifier.height(16.dp))
        BuffRatioField(
            buff.toString(),
            buff.total(),
            onChange = { ratio -> dispatch(Buff(ratio)) },
        )
        Spacer(Modifier.height(16.dp))
        InterestRatioField(
            interestRatio.toString(),
            interestRatio.total(),
            onChange = { ratio -> dispatch(InterestRatio(ratio)) },
        )
    }
}
