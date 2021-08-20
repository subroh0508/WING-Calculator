@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.atoms.TotalAppealsTable
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.data.TotalAppeal
import net.subroh0508.wingcalculator.data.TotalAppeals
import kotlin.math.abs

private enum class AppealType(val text: String) {
    VOCAL("Voアピール"), DANCE("Daアピール"), VISUAL("Viアピール");

    operator fun component1() = text

    fun next() = values()[(ordinal + 1) % 3]
    fun previous() = values()[(values().size + (ordinal - 1)) % 3]
}

@Composable
fun SimpleCalculateResult() {
    var appealTypeState by remember { mutableStateOf(AppealType.VOCAL) }

    val uiModel = SimpleCalculatorProviderContext.current

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
        TableSwitcher(
            appealTypeState,
            onClickBack = { appealTypeState = appealTypeState.previous() },
            onClickForward = { appealTypeState = appealTypeState.next() },
        )
    }
}

@Composable
private fun ColumnScope.TableSwitcher(
    type: AppealType,
    onClickBack: () -> Unit,
    onClickForward: () -> Unit,
) = Row(
    modifier = Modifier.padding(vertical = 8.dp)
        .align(Alignment.CenterHorizontally),
) {
    IconButton(
        onClick = onClickBack
    ) {
        Icon(
            Icons.Default.KeyboardArrowLeft,
            contentDescription = "back",
            modifier = Modifier.size(24.dp),
        )
    }
    Text(
        type.text,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.padding(horizontal = 16.dp)
            .align(Alignment.CenterVertically),
    )
    IconButton(
        onClick = onClickForward
    ) {
        Icon(
            Icons.Default.KeyboardArrowRight,
            contentDescription = "forward",
            modifier = Modifier.size(24.dp),
        )
    }
}

private fun TotalAppeals.toTableData() = Triple(
    vocal.map(TotalAppeal.Vocal::toTableData),
    dance.map(TotalAppeal.Dance::toTableData),
    visual.map(TotalAppeal.Visual::toTableData),
)

private fun TotalAppeal.toTableData() = listOf(toVocal, toDance, toVisual).let { (vo, da, vi) ->
    listOf(vo.toString(), da.toString(), vi.toString())
}
