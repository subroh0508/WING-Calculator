@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculateresultlayout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
fun CalculateResultTable() {
    var appealTypeState by remember { mutableStateOf(AppealType.VOCAL) }

    val uiModel = SimpleCalculatorProviderContext.current.uiModel

    Column {
        TotalAppealsTable(
            uiModel.totalAppeals.toTableData().let { (vo, da, vi) ->
                when (appealTypeState) {
                    AppealType.VOCAL -> vo
                    AppealType.DANCE -> da
                    AppealType.VISUAL -> vi
                }
            },
            modifier = Modifier.padding(top = 16.dp, start = 8.dp, end = 8.dp),
        )
        Switcher(
            appealTypeState,
            SwitcherOrientation.HORIZONTAL,
            onClickBack = { appealTypeState = appealTypeState.previous() },
            onClickForward = { appealTypeState = appealTypeState.next() },
        )
    }
}

private enum class AppealType(override val text: String) : SwitcherLabel {
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
