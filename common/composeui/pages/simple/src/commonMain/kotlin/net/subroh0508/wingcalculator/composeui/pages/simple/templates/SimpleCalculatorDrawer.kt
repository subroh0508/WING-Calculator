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
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.*
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.CalculatorResultLayout

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
    val constraints = LayoutConstraints(maxWidth)
    val drawerState = rememberResponsibleDrawerState(constraints, DrawerValue.Closed)

    ResponsibleDrawer(
        constraints,
        drawerContent = {
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
        },
        drawerState = drawerState,
    ) {
        SimpleCalculatorDrawerLayout(it, drawerState)
    }
}

@Composable
private fun SimpleCalculatorDrawerLayout(
    constraints: LayoutConstraints,
    drawerState: ResponsibleDrawerState,
) {
    if (constraints.panelCount == 1) {
        SimpleCalculatorBackdrop(drawerState)
        return
    }

    Row {
        BackLayerContent(modifier = Modifier.weight(1F))
        CalculatorResultLayout(
            BackdropScaffoldDefaults.HeaderHeight,
            constraints.panelCount,
            modifier = Modifier.weight(1F),
        )
    }
}
