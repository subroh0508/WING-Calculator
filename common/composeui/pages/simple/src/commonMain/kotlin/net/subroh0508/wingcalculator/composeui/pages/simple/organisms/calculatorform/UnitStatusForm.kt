@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculatorform

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.molecules.IdolStatusBox
import net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers.provideInputFormDispatcher

@Composable
fun UnitStatusForm(modifier: Modifier = Modifier) {
    val (uiModel, dispatch) = provideInputFormDispatcher()

    val (pIdol, sIdols) = uiModel.form

    Column(modifier = modifier) {
        IdolStatusBox(
            "プロデュースアイドル",
            pIdol.toList(),
            dispatch::invoke,
        )
        sIdols.forEachIndexed { i, sIdol ->
            Spacer(modifier = Modifier.height(16.dp))
            IdolStatusBox(
                "サポートアイドル(${i + 1})",
                sIdol.toList(),
                { vo, da, vi, _ -> dispatch(i, vo, da, vi) },
            )
        }
    }
}
