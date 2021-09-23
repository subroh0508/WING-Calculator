@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculatorform

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.appeal.model.Idol
import net.subroh0508.wingcalculator.composeui.components.atoms.NumberField
import net.subroh0508.wingcalculator.composeui.components.di.uiModel
import net.subroh0508.wingcalculator.composeui.components.themes.danceColor
import net.subroh0508.wingcalculator.composeui.components.themes.mentalColor
import net.subroh0508.wingcalculator.composeui.components.themes.visualColor
import net.subroh0508.wingcalculator.composeui.components.themes.vocalColor
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorProviderContext
import net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers.provideInputFormDispatcher
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel

@Composable
fun ColumnScope.UnitStatusForm() {
    val form = SimpleCalculatorProviderContext.current.uiModel.form

    ProduceIdolStatusForm(form)
    form.sIdols.mapIndexed { index, _ ->
        Spacer(modifier = Modifier.height(16.dp))
        SupportIdolStatusForm(index, form)
    }
}

@Composable
private fun ColumnScope.ProduceIdolStatusForm(
    form: SimpleCalculatorUiModel.Form,
) {
    var status by remember(form.id) { mutableStateOf(IdolStatus(form.pIdol)) }

    IdolStatusForm("プロデュースアイドル", status) { status = it }
}

@Composable
private fun ColumnScope.SupportIdolStatusForm(
    index: Int,
    form: SimpleCalculatorUiModel.Form,
) {
    var status by remember(form.id) { mutableStateOf(IdolStatus(form.sIdols[index])) }

    IdolStatusForm("サポートアイドル", status, index) { status = it }
}

@Composable
private fun ColumnScope.IdolStatusForm(
    label: String,
    status: IdolStatus,
    index: Int? = null,
    onStateChange: (IdolStatus) -> Unit,
) {
    val (_, dispatch) = provideInputFormDispatcher()

    LaunchedEffect(status) {
        val (vocal, dance, visual, mental) = status.toInt()

        if (index == null)
            dispatch(vo = vocal, da = dance, vi = visual, me = mental)
        else
            dispatch(index = index, vo = vocal, da = dance, vi = visual)
    }

    Text(
        label + (index?.let { "(${it + 1})" } ?: ""),
        style = MaterialTheme.typography.subtitle1,
    )
    Row {
        StatusField(
            status.vocal,
            label = "Vo",
            focusedColor = vocalColor,
            onChangeValue = { onStateChange(status.copy(vocal = it)) },
            modifier = Modifier.weight(1F),
        )
        Spacer(Modifier.width(8.dp))
        StatusField(
            status.dance,
            label = "Da",
            focusedColor = danceColor,
            onChangeValue = { onStateChange(status.copy(dance = it)) },
            modifier = Modifier.weight(1F),
        )
        Spacer(Modifier.width(8.dp))
        StatusField(
            status.visual,
            label = "Vi",
            focusedColor = visualColor,
            onChangeValue = { onStateChange(status.copy(visual = it)) },
            modifier = Modifier.weight(1F),
        )
        Spacer(Modifier.width(8.dp))
        if (status.mental != null)
            StatusField(
                status.mental,
                label = "Me",
                focusedColor = mentalColor,
                onChangeValue = { onStateChange(status.copy(mental = it)) },
                modifier = Modifier.weight(1F),
            )
        else
            Spacer(Modifier.weight(1F))
    }
}

private val STATUS_NUMBER_REGEX = """^(0|[1-9][0-9]{0,4})*$""".toRegex()

@Composable
private fun StatusField(
    value: String,
    label: String,
    focusedColor: Color,
    onChangeValue: (String) -> Unit,
    modifier: Modifier = Modifier,
) = NumberField(
    value,
    label = label,
    focusedColor = focusedColor,
    regex = STATUS_NUMBER_REGEX,
    onChangeValue = onChangeValue,
    modifier = modifier,
)

private data class IdolStatus(
    val vocal: String = "",
    val dance: String = "",
    val visual: String = "",
    val mental: String? = null,
) {
    constructor(idol: Idol) : this(
        idol.vocal.toString(),
        idol.dance.toString(),
        idol.visual.toString(),
        if (idol is Idol.Produce) idol.mental.toString() else null,
    )

    fun toInt(): List<Int?> = listOf(vocal, dance, visual, mental).map {
        if (it.isNullOrBlank()) {
            return@map 0
        }

        it.toIntOrNull()
    }
}
