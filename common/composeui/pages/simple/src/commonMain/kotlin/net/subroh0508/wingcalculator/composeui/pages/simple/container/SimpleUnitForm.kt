@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.container

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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

    val (pIdol, sIdols) = uiModel

    fun handleOnPIdolStateChange(vo: Int?, da: Int?, vi: Int?) {
        onChangeUiModel(uiModel.copy(
            pIdol = Idol.Produce(
                vo?.let(::Vocal) ?: pIdol.vocal,
                da?.let(::Dance) ?: pIdol.dance,
                vi?.let(::Visual) ?: pIdol.visual,
            ),
        ))
    }

    fun handleOnSIdolStateChange(index: Int, vo: Int?, da: Int?, vi: Int?) {
        val newSIdol = sIdols[index].let {
            Idol.Support(
                vo?.let(::Vocal) ?: it.vocal,
                da?.let(::Dance) ?: it.dance,
                vi?.let(::Visual) ?: it.visual,
            )
        }

        onChangeUiModel(uiModel.copy(
            sIdols = sIdols.mapIndexed { i, sIdol -> if (i == index) newSIdol else sIdol },
        ))
    }

    Column {
        IdolStatusBox(
            "プロデュースアイドル",
            ::handleOnPIdolStateChange,
            modifier = Modifier.padding(bottom = 16.dp),
        )
        sIdols.forEachIndexed { i, _ ->
            IdolStatusBox(
                "サポートアイドル(${i + 1})",
                { vo, da, vi -> handleOnSIdolStateChange(i, vo, da, vi) },
                modifier = Modifier.padding(bottom = 16.dp),
            )
        }
    }
}
