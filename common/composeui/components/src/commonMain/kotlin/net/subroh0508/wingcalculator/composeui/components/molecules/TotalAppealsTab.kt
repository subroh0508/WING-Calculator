@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.atoms.TotalAppealsTable

private enum class TabTitle(val text: String) {
    VOCAL("Voアピール"), DANCE("Daアピール"), VISUAL("Viアピール");

    operator fun component1() = text
}

@Composable
fun TotalAppealsTab(
    vocal: List<List<String>>,
    dance: List<List<String>>,
    visual: List<List<String>>,
) {
    var tabState by remember { mutableStateOf(0) }

    Column {
        TabRow(
            tabState,
            backgroundColor = MaterialTheme.colors.background,
        ) {
            TabTitle.values().forEachIndexed { i, (text) ->
                Tab(
                    selected = tabState == i,
                    onClick = { tabState = i },
                ) {
                    Text(
                        text,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(16.dp),
                    )
                }
            }
        }

        TotalAppealsTable(
            when (TabTitle.values()[tabState]) {
                TabTitle.VOCAL -> vocal
                TabTitle.DANCE -> dance
                TabTitle.VISUAL -> visual
            },
            modifier = Modifier.padding(16.dp),
        )
    }
}
