@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.appeal.model.Appeal
import net.subroh0508.wingcalculator.appeal.model.TotalAppeal
import net.subroh0508.wingcalculator.appeal.model.TotalAppeals
import net.subroh0508.wingcalculator.composeui.components.atoms.Table
import net.subroh0508.wingcalculator.composeui.components.di.uiModel
import net.subroh0508.wingcalculator.composeui.components.molecules.Switcher
import net.subroh0508.wingcalculator.composeui.components.molecules.SwitcherLabel
import net.subroh0508.wingcalculator.composeui.components.molecules.SwitcherOrientation
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.preference.model.AppPreference

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

    Table(
        JUDGES,
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

enum class Judge(override val text: String) : SwitcherLabel {
    VOCAL("Vo審査員"), DANCE("Da審査員"), VISUAL("Vi審査員");

    operator fun component1() = text

    fun next() = values().let { it[(ordinal + 1) % it.size] }
    fun previous() = values().let { it[(it.size + (ordinal - 1)) % it.size] }
}

private val JUDGES = listOf("Vo審査員", "Da審査員", "Vi審査員")
private val IDOLS = listOf("P", "S1", "S2", "S3", "S4")

private fun TotalAppeals.toTableData() = toTableData(AppPreference.Table.JUDGE)

private fun TotalAppeals.toTableData(type: AppPreference.Table): List<Map<String, List<String>>> = when (type) {
    AppPreference.Table.JUDGE -> listOf(vocal, dance, visual).map { unit ->
        unit.foldIndexed(mapOf()) { i, acc, appeal ->
            acc + mapOf(IDOLS[i] to listOf(appeal.toVocal, appeal.toDance, appeal.toVisual).map(Appeal::toString))
        }
    }
    AppPreference.Table.APPEAL -> listOf(toVocal, toDance, toVisual).map { unit ->
        unit.foldIndexed(mapOf()) { i, acc, appeal ->
            acc + mapOf(IDOLS[i] to appeal.map(Appeal::toString))
        }
    }
}
