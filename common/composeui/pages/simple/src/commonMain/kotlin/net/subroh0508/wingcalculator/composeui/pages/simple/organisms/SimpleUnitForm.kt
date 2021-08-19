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
fun SimpleUnitForm() {
    val uiModel = SimpleCalculatorProviderContext.current
    val onChangeUiModel = SimpleCalculatorDispatcherContext.current

    val (_, sIdols) = uiModel

    val handleOnPIdolStateChange = remember(uiModel) {
        { vo: Int?, da: Int?, vi: Int? ->
            onChangeUiModel(uiModel.copy(
                pIdol = Idol.Produce(
                    vo?.let(::Vocal) ?: uiModel.pIdol.vocal,
                    da?.let(::Dance) ?: uiModel.pIdol.dance,
                    vi?.let(::Visual) ?: uiModel.pIdol.visual,
                ),
            ))
        }
    }
    val handleOnSIdolStateChange = remember(uiModel) {
        { index: Int, vo: Int?, da: Int?, vi: Int? ->
            val newSIdol = uiModel.sIdols[index].let {
                Idol.Support(
                    vo?.let(::Vocal) ?: it.vocal,
                    da?.let(::Dance) ?: it.dance,
                    vi?.let(::Visual) ?: it.visual,
                )
            }

            onChangeUiModel(
                uiModel.copy(
                    sIdols = uiModel.sIdols.mapIndexed { i, sIdol -> if (i == index) newSIdol else sIdol },
                ),
            )
        }
    }

    BoxWithConstraints {
        val widthModifier = when {
            maxWidth < 480.dp -> Modifier.requiredSizeIn(minWidth = 320.dp)
            else -> Modifier.requiredSizeIn(maxWidth = 480.dp)
        }

        Column {
            IdolStatusBox(
                "プロデュースアイドル",
                handleOnPIdolStateChange,
                modifier = widthModifier.padding(bottom = 16.dp),
            )
            sIdols.forEachIndexed { i, _ ->
                IdolStatusBox(
                    "サポートアイドル(${i + 1})",
                    { vo, da, vi -> handleOnSIdolStateChange(i, vo, da, vi) },
                    modifier = widthModifier.padding(bottom = 16.dp),
                )
            }
        }
    }
}
