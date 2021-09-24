@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.appframe

import androidx.compose.foundation.layout.*
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.appframe.constraints.SimpleCalculatorPageConstraints
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.ResponsibleDrawer
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.rememberResponsibleDrawerState
import net.subroh0508.wingcalculator.composeui.components.themes.AppTheme
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorPage

@Composable
fun AppFrame() = AppTheme {
    BoxWithConstraints {
        var page by rememberPage(maxWidth) { Pages.SimpleCalculator(maxWidth) }

        val drawerState = rememberResponsibleDrawerState(page.constraints, DrawerValue.Closed)

        ResponsibleDrawer(
            page.constraints,
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
            content = {
                when (page) {
                    is Pages.SimpleCalculator -> SimpleCalculatorPage(
                        (page.constraints as SimpleCalculatorPageConstraints).panel, drawerState
                    )
                }
            },
        )
    }
}
