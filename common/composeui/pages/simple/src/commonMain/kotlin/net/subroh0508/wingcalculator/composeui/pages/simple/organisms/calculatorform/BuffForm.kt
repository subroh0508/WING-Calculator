@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculatorform

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.appeal.model.*
import net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers.provideInputFormDispatcher
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculatorform.selector.AppealJudgeSelector
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculatorform.selector.AppealRatioSelector
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculatorform.selector.WeekSelector
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculatorform.textfield.BuffRatioField
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculatorform.textfield.InterestRatioField

@Composable
fun ColumnScope.BuffForm() {
    val (uiModel, dispatch) = provideInputFormDispatcher()

    val (_, _, weekData, appealRatio, buff, appealJudge, interestRatio) = uiModel.form

    WeekSelector(
        weekData.season,
        weekData.week,
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
