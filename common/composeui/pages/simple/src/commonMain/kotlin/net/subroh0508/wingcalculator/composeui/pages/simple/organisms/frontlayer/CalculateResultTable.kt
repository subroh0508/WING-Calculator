@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms.frontlayer

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.appeal.model.Appeal
import net.subroh0508.wingcalculator.appeal.model.buffform.MemoryJudge
import net.subroh0508.wingcalculator.appeal.model.TotalAppeals
import net.subroh0508.wingcalculator.composeui.components.atoms.Table
import net.subroh0508.wingcalculator.composeui.components.di.uiModel
import net.subroh0508.wingcalculator.composeui.components.molecules.*
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.composeui.pages.simple.TableTypePreferenceProviderContext
import net.subroh0508.wingcalculator.preference.model.AppPreference

@Composable
fun CalculateResultTable(modifier: Modifier = Modifier) {
    val tableType = TableTypePreferenceProviderContext.current
    val tableData = SimpleCalculatorProviderContext.current.uiModel.totalAppeals.toTableData(tableType)

    var tableLabel by remember(tableType) {
        mutableStateOf(
            when (tableType) {
                AppPreference.Table.APPEAL -> AppealType.VOCAL
                AppPreference.Table.JUDGE -> Judge.VOCAL
            }
        )
    }

    Box(modifier) {
        Column(Modifier.align(Alignment.Center)) {
            CalculateResultTableWithSwitcher(tableLabel, tableData) {
                tableLabel = it
            }
        }
    }
}

@Composable
fun CalculateResultTables(
    modifier: Modifier = Modifier,
    onSwitcherLabelChanged: ((SwitcherLabel) -> Unit)? = null,
) {
    val tableType = TableTypePreferenceProviderContext.current
    val tableData = SimpleCalculatorProviderContext.current.uiModel.totalAppeals.toTableData(tableType)

    val labels = when (tableType) {
        AppPreference.Table.APPEAL -> AppealType.values()
        AppPreference.Table.JUDGE -> Judge.values()
        else -> return
    }

    Column(modifier) {
        labels.forEachIndexed { i, type ->
            CalculateResultTableWithSwitcher(type, tableData, onSwitcherLabelChanged)

            if (i < (labels.size - 1)) {
                Divider(Modifier.padding(horizontal = 8.dp))
            }
        }
    }
}

@Composable
private fun ColumnScope.CalculateResultTableWithSwitcher(
    tableLabel: SwitcherLabel,
    tableData: List<Map<String, List<String>>>,
    onSwitcherLabelChanged: ((SwitcherLabel) -> Unit)? = null,
) {
    fun onClickBack(label: SwitcherLabel) = onSwitcherLabelChanged?.invoke(label.previous())
    fun onClickForward(label: SwitcherLabel) = onSwitcherLabelChanged?.invoke(label.next())

    val (header, columns) = when (tableLabel) {
        is AppealType -> header(tableLabel) to tableData.let { (vo, da, vi, memory) ->
            when (tableLabel) {
                AppealType.VOCAL -> vo
                AppealType.DANCE -> da
                AppealType.VISUAL -> vi
                AppealType.MEMORY -> memory
            }
        }
        is Judge -> header(tableLabel) to tableData.let { (vo, da, vi, memory) ->
            when (tableLabel) {
                Judge.VOCAL -> vo
                Judge.DANCE -> da
                Judge.VISUAL -> vi
                Judge.MEMORY -> memory
            }
        }
        else -> return
    }

    Table(
        header.map(SwitcherLabel::text), columns,
        height = IDOLS.size,
        modifier = Modifier.padding(top = 16.dp, start = 8.dp, end = 8.dp),
    )
    Switcher(
        tableLabel,
        SwitcherOrientation.HORIZONTAL,
        onClickBack = onSwitcherLabelChanged?.let { { onClickBack(tableLabel) } },
        onClickForward = onSwitcherLabelChanged?.let { { onClickForward(tableLabel) } },
    )
}

enum class AppealType(override val text: String) : SwitcherLabel {
    VOCAL("Voアピール"), DANCE("Daアピール"), VISUAL("Viアピール"), MEMORY("思い出");

    override fun next() = nextEnum()
    override fun previous() = previousEnum()

    companion object { const val LABEL = "アピール種別" }
}

enum class Judge(override val text: String) : SwitcherLabel {
    VOCAL("Vo審査員"), DANCE("Da審査員"), VISUAL("Vi審査員"), MEMORY("思い出");

    override fun next() = nextEnum()
    override fun previous() = previousEnum()

    companion object { const val LABEL = "審査員" }
}

private fun header(tableLabel: SwitcherLabel): List<SwitcherLabel> = when {
    listOf(AppealType.MEMORY, Judge.MEMORY).contains(tableLabel) -> Judge.values()
    tableLabel is AppealType -> Judge.values()
    tableLabel is Judge -> AppealType.values()
    else -> arrayOf()
}.toList().dropLast(1)

private val IDOLS = listOf("P", "S1", "S2", "S3", "S4")

private fun TotalAppeals.toTableData(type: AppPreference.Table): List<Map<String, List<String>>> = when (type) {
    AppPreference.Table.APPEAL -> listOf(vocal, dance, visual).toTableData()
    AppPreference.Table.JUDGE -> listOf(toVocal, toDance, toVisual).toTableData()
} + MemoryJudge.values().associate { memoryJudge ->
    memoryJudge.toString() to Judge.values().dropLast(1).map {
        memories[it.ordinal][memoryJudge.ordinal].toString()
    }
}

private fun List<TotalAppeals.Unit>.toTableData(): List<Map<String, List<String>>> = map { unit ->
    unit.foldIndexed(mapOf()) { i, acc, appeal ->
        acc + mapOf(IDOLS[i] to appeal.map(Appeal::toString))
    }
}
