@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.molecules.IdolStatusBox
import net.subroh0508.wingcalculator.composeui.pages.simple.provideInputFormDispatcher

@Composable
fun SimpleUnitForm(modifier: Modifier = Modifier) {
    val (uiModel, dispatch) = provideInputFormDispatcher()

    val (pIdol, sIdols) = uiModel.form

    val handleOnPIdolStateChange = remember(uiModel) { { vo: Int?, da: Int?, vi: Int?, me: Int? -> dispatch(vo, da, vi, me) } }
    val handleOnSIdolStateChange = remember(uiModel) { { index: Int, vo: Int?, da: Int?, vi: Int? -> dispatch(index, vo, da, vi) } }

    Column(modifier = modifier) {
        IdolStatusBox(
            "プロデュースアイドル",
            pIdol.toList(),
            handleOnPIdolStateChange,
        )
        sIdols.forEachIndexed { i, sIdol ->
            Spacer(modifier = Modifier.height(16.dp))
            IdolStatusBox(
                "サポートアイドル(${i + 1})",
                sIdol.toList(),
                { vo, da, vi, _ -> handleOnSIdolStateChange(i, vo, da, vi) },
            )
        }
    }
}
