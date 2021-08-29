@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.molecules.IdolStatusBox
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorDispatcherContext
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.appeal.model.Dance
import net.subroh0508.wingcalculator.appeal.model.Idol
import net.subroh0508.wingcalculator.appeal.model.Visual
import net.subroh0508.wingcalculator.appeal.model.Vocal
import net.subroh0508.wingcalculator.composeui.pages.simple.provideInputFormDispatcher

@Composable
fun SimpleUnitForm(modifier: Modifier = Modifier) {
    val (uiModel, dispatch) = provideInputFormDispatcher()

    val (_, sIdols) = uiModel.form

    val handleOnPIdolStateChange = remember(uiModel) { { vo: Int?, da: Int?, vi: Int? -> dispatch(vo, da, vi) } }
    val handleOnSIdolStateChange = remember(uiModel) { { index: Int, vo: Int?, da: Int?, vi: Int? -> dispatch(index, vo, da, vi) } }

    Column(modifier = modifier) {
        IdolStatusBox(
            "プロデュースアイドル",
            handleOnPIdolStateChange,
        )
        sIdols.forEachIndexed { i, _ ->
            Spacer(modifier = Modifier.height(16.dp))
            IdolStatusBox(
                "サポートアイドル(${i + 1})",
                { vo, da, vi -> handleOnSIdolStateChange(i, vo, da, vi) },
            )
        }
    }
}
