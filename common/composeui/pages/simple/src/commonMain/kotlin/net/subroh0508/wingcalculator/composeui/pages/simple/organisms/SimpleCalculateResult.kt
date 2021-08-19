@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.atoms.TotalAppealsTable
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext

@Composable
fun SimpleCalculateResult() {
    val stateHorizontal = rememberScrollState()

    val uiModel = SimpleCalculatorProviderContext.current

    val tableModifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)

    Row(modifier = Modifier.horizontalScroll(stateHorizontal)) {
        TotalAppealsTable(
            uiModel.totalAppeals.vocal.let { (vo, da, vi) ->
                listOf(
                    listOf(vo.toString(), da.toString(), vi.toString()),
                    listOf("0", "0", "0"),
                    listOf("0", "0", "0"),
                    listOf("0", "0", "0"),
                    listOf("0", "0", "0"),
                )
            },
            modifier = tableModifier,
        )

        TotalAppealsTable(
            uiModel.totalAppeals.dance.let { (vo, da, vi) ->
                listOf(
                    listOf(vo.toString(), da.toString(), vi.toString()),
                    listOf("0", "0", "0"),
                    listOf("0", "0", "0"),
                    listOf("0", "0", "0"),
                    listOf("0", "0", "0"),
                )
            },
            modifier = tableModifier,
        )
        TotalAppealsTable(
            uiModel.totalAppeals.visual.let { (vo, da, vi) ->
                listOf(
                    listOf(vo.toString(), da.toString(), vi.toString()),
                    listOf("0", "0", "0"),
                    listOf("0", "0", "0"),
                    listOf("0", "0", "0"),
                    listOf("0", "0", "0"),
                )
            },
            modifier = tableModifier,
        )
    }
}
