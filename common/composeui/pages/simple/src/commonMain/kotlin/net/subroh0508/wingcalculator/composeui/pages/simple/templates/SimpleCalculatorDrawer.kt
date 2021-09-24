@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.*

enum class LayoutConstraints(
    override val drawer: DrawerType,
    val panelCount: Int,
    private val range: ClosedRange<Dp>,
) : DrawerConstraints, ClosedRange<Dp> by range {
    ONE_PANEL_MODAL(DrawerType.Modal, 1, 0.dp..368.dp),
    ONE_PANEL_SHRINKABLE_MODAL(DrawerType.ShrinkableModal, 1, 369.dp..688.dp),
    TWO_PANELS_SHRINKABLE_MODAL(DrawerType.ShrinkableModal, 2, 689.dp..896.dp),
    TWO_PANELS_SHRINKABLE_PERSIST(DrawerType.ShrinkablePersist, 2, 897.dp..Dp.Infinity);

    companion object {
        operator fun invoke(maxWidth: Dp) = when (maxWidth) {
            in ONE_PANEL_MODAL -> ONE_PANEL_MODAL
            in ONE_PANEL_SHRINKABLE_MODAL -> ONE_PANEL_SHRINKABLE_MODAL
            in TWO_PANELS_SHRINKABLE_MODAL -> TWO_PANELS_SHRINKABLE_MODAL
            else -> TWO_PANELS_SHRINKABLE_PERSIST
        }
    }
}

@Composable
fun SimpleCalculatorDrawer() = BoxWithConstraints {
    val drawerState = rememberResponsibleDrawerState(
        LayoutConstraints(maxWidth),
        DrawerValue.Closed,
    )

    ResponsibleDrawer(
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
        drawerState = drawerState,
    ) {
        SimpleCalculatorBackdrop(drawerState)
    }
}
