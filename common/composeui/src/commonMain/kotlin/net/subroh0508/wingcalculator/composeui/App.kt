package net.subroh0508.wingcalculator.composeui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import net.subroh0508.wingcalculator.data.*

@Composable
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }
    var pIdolState by remember { mutableStateOf(Idol.Produce()) }
    var sIdolsState by remember { mutableStateOf(listOf(Idol.Support(), Idol.Support(), Idol.Support(), Idol.Support())) }

    MaterialTheme {
        Column {
            ProduceIdolRow(pIdolState) { pIdol -> pIdolState = pIdol }
            sIdolsState.forEachIndexed { i, _ ->
                SupportIdolRow(sIdolsState, i) { sIdols -> sIdolsState = sIdols }
            }
        }
    }
}

@Composable
fun ProduceIdolRow(pIdol: Idol.Produce, onChange: (Idol.Produce) -> Unit) {
    Row {
        Text("pアイドル")
        OutlinedTextField(
            pIdol.vocal.toString(),
            onValueChange = { s ->
                convert(s, ::Vocal)?.let { onChange(pIdol.copy(vocal = it)) }
            },
        )
        OutlinedTextField(
            pIdol.dance.toString(),
            onValueChange = { s ->
                convert(s, ::Dance)?.let { onChange(pIdol.copy(dance = it)) }
            },
        )
        OutlinedTextField(
            pIdol.visual.toString(),
            onValueChange = { s ->
                convert(s, ::Visual)?.let { onChange(pIdol.copy(visual = it)) }
            },
        )
    }
}

@Composable
fun SupportIdolRow(sIdols: List<Idol.Support>, index: Int, onChange: (List<Idol.Support>) -> Unit) {
    val sIdol = sIdols[index]

    fun handleOnValueChange(
        newSupportIdol: Idol.Support,
        index: Int,
    ) = onChange(sIdols.mapIndexed { i, s -> if (i == index) newSupportIdol else s })

    Row {
        Text("sアイドル(${index + 1})")
        OutlinedTextField(
            sIdol.vocal.toString(),
            onValueChange = { s ->
                convert(s, ::Vocal)?.let { handleOnValueChange(sIdol.copy(vocal = it), index) }
            },
        )
        OutlinedTextField(
            sIdol.dance.toString(),
            onValueChange = { s ->
                convert(s, ::Dance)?.let { handleOnValueChange(sIdol.copy(dance = it), index) }
            },
        )
        OutlinedTextField(
            sIdol.visual.toString(),
            onValueChange = { s ->
                convert(s, ::Visual)?.let { handleOnValueChange(sIdol.copy(visual = it), index) }
            },
        )
    }
}

private fun <T: Status> convert(s: String, converter: (Int) -> T?) = s.toIntOrNull()?.let(converter)
