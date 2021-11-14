@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.appframe.preference

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import net.subroh0508.wingcalculator.composeui.appframe.Strings
import net.subroh0508.wingcalculator.composeui.appframe.constraints.AppPreferenceMinWidth
import net.subroh0508.wingcalculator.composeui.appframe.getString
import net.subroh0508.wingcalculator.composeui.appframe.preference.forms.DarkTheme
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.TopAppBarWithNavigation
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.DrawerType
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.ResponsiveDrawerState
import net.subroh0508.wingcalculator.preference.model.AppPreference

internal val AppPreferenceFormsHorizontalPadding = 16.dp

@Composable
fun AppPreferencePage(
    drawerType: DrawerType,
    preference: AppPreference,
    drawerState: ResponsiveDrawerState,
) {
    val scope = rememberCoroutineScope()

    Surface(Modifier.fillMaxSize()) {
        Column {
            TopAppBarWithNavigation(
                getString(Strings.PageTitlePreference),
                onNavigationClick =
                    if (drawerType == DrawerType.Modal)
                        { { scope.launch { drawerState.open() } } }
                    else
                        null,
            )

            BoxWithConstraints(Modifier.fillMaxWidth()) {
                Column(widthConstraintsModifier.align(Alignment.Center)) {
                    AppPreferenceForms(preference)
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.AppPreferenceForms(preference: AppPreference) {
    DarkTheme(preference)
    Divider(Modifier.padding(horizontal = AppPreferenceFormsHorizontalPadding))
}

private val BoxWithConstraintsScope.widthConstraintsModifier get() = when {
    maxWidth < (AppPreferenceMinWidth * 2) -> Modifier
    else -> Modifier.requiredWidthIn(max = AppPreferenceMinWidth * 2)
}

