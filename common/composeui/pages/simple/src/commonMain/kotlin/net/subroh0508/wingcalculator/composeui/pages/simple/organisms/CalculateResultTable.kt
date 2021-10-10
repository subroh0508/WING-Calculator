@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.appeal.model.TotalAppeal
import net.subroh0508.wingcalculator.appeal.model.TotalAppeals
import net.subroh0508.wingcalculator.composeui.components.atoms.TotalAppealsTable
import net.subroh0508.wingcalculator.composeui.components.di.uiModel
import net.subroh0508.wingcalculator.composeui.components.molecules.Switcher
import net.subroh0508.wingcalculator.composeui.components.molecules.SwitcherLabel
import net.subroh0508.wingcalculator.composeui.components.molecules.SwitcherOrientation
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext

@Composable
fun CalculateResultTables(
    vararg appealType: AppealType,
    modifier: Modifier = Modifier,
    onAppealTypeChanged: ((AppealType) -> Unit) = {},
) = Column(modifier) {
    appealType.forEachIndexed { i, type ->
        CalculateResultTable(
            type,
            SimpleCalculatorProviderContext.current.uiModel.totalAppeals,
            if (appealType.size == 1) onAppealTypeChanged else null,
        )

        if (appealType.size != 1 && i < (appealType.size - 1)) {
            Divider(Modifier.padding(horizontal = 8.dp))
        }
    }
}

@Composable
private fun ColumnScope.CalculateResultTable(
    appealType: AppealType,
    totalAppeals: TotalAppeals,
    onAppealTypeChanged: ((AppealType) -> Unit)? = null,
) {
    fun onClickBack(appealType: AppealType) = onAppealTypeChanged?.invoke(appealType.previous())
    fun onClickForward(appealType: AppealType) = onAppealTypeChanged?.invoke(appealType.next())

    TotalAppealsTable(
        totalAppeals.toTableData().let { (vo, da, vi) ->
            when (appealType) {
                AppealType.VOCAL -> vo
                AppealType.DANCE -> da
                AppealType.VISUAL -> vi
            }
        },
        modifier = Modifier.padding(top = 16.dp, start = 8.dp, end = 8.dp),
    )
    Switcher(
        appealType,
        SwitcherOrientation.HORIZONTAL,
        onClickBack = onAppealTypeChanged?.let { { onClickBack(appealType) } },
        onClickForward = onAppealTypeChanged?.let { { onClickForward(appealType) } },
    )
}

enum class AppealType(override val text: String) : SwitcherLabel {
    VOCAL("Voアピール"), DANCE("Daアピール"), VISUAL("Viアピール");

    operator fun component1() = text

    fun next() = values()[(ordinal + 1) % 3]
    fun previous() = values()[(values().size + (ordinal - 1)) % 3]
}

private fun TotalAppeals.toTableData() = Triple(
    vocal.map(TotalAppeal.Vocal::toTableData),
    dance.map(TotalAppeal.Dance::toTableData),
    visual.map(TotalAppeal.Visual::toTableData),
)

private fun TotalAppeal.toTableData() = listOf(toVocal, toDance, toVisual).let { (vo, da, vi) ->
    listOf(vo.toString(), da.toString(), vi.toString())
}
