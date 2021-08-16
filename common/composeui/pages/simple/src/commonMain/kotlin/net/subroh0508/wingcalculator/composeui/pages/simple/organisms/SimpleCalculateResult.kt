@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.runtime.Composable
import net.subroh0508.wingcalculator.composeui.components.molecules.TotalAppealsTab
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext

@Composable
fun SimpleCalculateResult() {
    val uiModel = SimpleCalculatorProviderContext.current

    TotalAppealsTab(
        uiModel.totalAppeals.vocal.let { (vo, da, vi) ->
            listOf(
                listOf(vo.toString(), "0", "0", "0", "0"),
                listOf(da.toString(), "0", "0", "0", "0"),
                listOf(vi.toString(), "0", "0", "0", "0"),
            )
        },
        uiModel.totalAppeals.dance.let { (vo, da, vi) ->
            listOf(
                listOf(vo.toString(), "0", "0", "0", "0"),
                listOf(da.toString(), "0", "0", "0", "0"),
                listOf(vi.toString(), "0", "0", "0", "0"),
            )
        },
        uiModel.totalAppeals.visual.let { (vo, da, vi) ->
            listOf(
                listOf(vo.toString(), "0", "0", "0", "0"),
                listOf(da.toString(), "0", "0", "0", "0"),
                listOf(vi.toString(), "0", "0", "0", "0"),
            )
        },
    )
}
