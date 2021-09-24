@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.ShrinkableDrawer
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.ShrinkableDrawerValue
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.rememberShrinkableDrawerState

@Composable
fun SimpleCalculatorDrawer() {
    val drawerState = rememberShrinkableDrawerState(ShrinkableDrawerValue.Shrink)

    ShrinkableDrawer(
        drawerContent = {
            Column {
                IconButton(
                    onClick = {},
                    modifier = Modifier.padding(end = 8.dp),
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "back",
                        modifier = Modifier.size(24.dp),
                    )
                }

                Row {
                    Icon(
                        Icons.Default.Settings,
                        contentDescription = "back",
                        modifier = Modifier.size(48.dp)
                            .padding(12.dp),
                    )

                    Text(
                        "アプリ設定",
                        softWrap = false,
                        modifier = Modifier.weight(1F),
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        SimpleCalculatorBackdrop(drawerState)
    }
}
