@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.molecules.IdolStatusBox
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorDispatcherContext
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.data.Dance
import net.subroh0508.wingcalculator.data.Idol
import net.subroh0508.wingcalculator.data.Visual
import net.subroh0508.wingcalculator.data.Vocal

@Composable
fun SimpleUnitForm(modifier: Modifier = Modifier) {
    val uiModel = SimpleCalculatorProviderContext.current
    val onChangeUiModel = SimpleCalculatorDispatcherContext.current

    val (_, sIdols) = uiModel.form

    val handleOnPIdolStateChange = remember(uiModel) {
        { vo: Int?, da: Int?, vi: Int? ->
            onChangeUiModel(uiModel.input(
                pIdol = Idol.Produce(
                    vo?.let(::Vocal) ?: uiModel.form.pIdol.vocal,
                    da?.let(::Dance) ?: uiModel.form.pIdol.dance,
                    vi?.let(::Visual) ?: uiModel.form.pIdol.visual,
                ),
            ))
        }
    }
    val handleOnSIdolStateChange = remember(uiModel) {
        { index: Int, vo: Int?, da: Int?, vi: Int? ->
            val newSIdol = uiModel.form.sIdols[index].let {
                Idol.Support(
                    vo?.let(::Vocal) ?: it.vocal,
                    da?.let(::Dance) ?: it.dance,
                    vi?.let(::Visual) ?: it.visual,
                )
            }

            onChangeUiModel(
                uiModel.input(
                    sIdols = uiModel.form.sIdols.mapIndexed { i, sIdol -> if (i == index) newSIdol else sIdol },
                ),
            )
        }
    }

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
