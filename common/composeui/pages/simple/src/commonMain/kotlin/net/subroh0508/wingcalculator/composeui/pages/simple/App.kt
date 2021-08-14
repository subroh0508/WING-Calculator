package net.subroh0508.wingcalculator.composeui.pages.simple

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

data class IdolStatus(
    val vocal: String = "",
    val dance: String = "",
    val visual: String = "",
) {
    fun toInt(): List<Int?> = listOf(vocal, dance, visual).map {
        if (it.isBlank()) {
            return@map 0
        }

        it.toIntOrNull()
    }
}

@Composable
fun ProduceIdolRow(pIdol: Idol.Produce, onChange: (Idol.Produce) -> Unit) {
    var status by remember { mutableStateOf(IdolStatus()) }

    LaunchedEffect(status) {
        val (vocal, dance, visual) = status.toInt()

        onChange(
            Idol.Produce(
                vocal?.let(::Vocal) ?: pIdol.vocal,
                dance?.let(::Dance) ?: pIdol.dance,
                visual?.let(::Visual) ?: pIdol.visual,
            )
        )
    }

    Row {
        Text("pアイドル")
        OutlinedTextField(
            status.vocal,
            onValueChange = { s -> status = status.copy(vocal = s) },
        )
        OutlinedTextField(
            status.dance,
            onValueChange = { s -> status = status.copy(dance = s) },
        )
        OutlinedTextField(
            status.visual,
            onValueChange = { s -> status = status.copy(visual = s) },
        )
    }
}

@Composable
fun SupportIdolRow(sIdols: List<Idol.Support>, index: Int, onChange: (List<Idol.Support>) -> Unit) {
    val sIdol = sIdols[index]

    var status by remember { mutableStateOf(IdolStatus()) }

    LaunchedEffect(status) {
        val (vocal, dance, visual) = status.toInt()

        val newSupportIdol = Idol.Support(
            vocal?.let(::Vocal) ?: sIdol.vocal,
            dance?.let(::Dance) ?: sIdol.dance,
            visual?.let(::Visual) ?: sIdol.visual,
        )

        onChange(sIdols.mapIndexed { i, s -> if (i == index) newSupportIdol else s })
    }

    Row {
        Text("sアイドル(${index + 1})")
        OutlinedTextField(
            status.vocal,
            onValueChange = { s -> status = status.copy(vocal = s) },
        )
        OutlinedTextField(
            status.dance,
            onValueChange = { s -> status = status.copy(dance = s) },
        )
        OutlinedTextField(
            status.visual,
            onValueChange = { s -> status = status.copy(visual = s) },
        )
    }
}
