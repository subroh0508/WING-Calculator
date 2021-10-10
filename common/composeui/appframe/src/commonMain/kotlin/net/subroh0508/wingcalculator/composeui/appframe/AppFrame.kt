@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.appframe

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import net.subroh0508.wingcalculator.composeui.appframe.constraints.panel
import net.subroh0508.wingcalculator.composeui.appframe.menu.DrawerHeader
import net.subroh0508.wingcalculator.composeui.appframe.menu.DrawerMenuItem
import net.subroh0508.wingcalculator.composeui.components.di.getKoin
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.ResponsiveDrawer
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.ResponsiveDrawerState
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.rememberResponsiveDrawerState
import net.subroh0508.wingcalculator.composeui.components.themes.AppTheme
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorPage
import net.subroh0508.wingcalculator.preference.model.AppPreference
import net.subroh0508.wingcalculator.usecase.preference.FetchAppPreferenceUseCase

@Composable
fun AppFrame() = BoxWithConstraints {
    var page: Pages by rememberPage(maxWidth) { Pages.SimpleCalculator(maxWidth) }
    val drawerState = rememberResponsiveDrawerState(page.constraints, DrawerValue.Closed)

    val koin = getKoin()
    val fetchAppPreferenceUseCase: FetchAppPreferenceUseCase = remember(koin) { koin.get() }
    var preference: AppPreference by remember(koin) { mutableStateOf(AppPreference()) }

    LaunchedEffect(koin) { preference = fetchAppPreferenceUseCase.execute() }

    ThemedAppFrame(preference, page, drawerState) { page = it }
}

@Composable
private fun ThemedAppFrame(
    preference: AppPreference,
    page: Pages,
    drawerState: ResponsiveDrawerState,
    onPageChanged: (Pages) -> Unit,
) = AppTheme(
    darkTheme = if (preference.theme == AppPreference.Theme.SYSTEM)
                    isSystemInDarkTheme()
                else
                    preference.theme == AppPreference.Theme.DARK,
) {
    ResponsiveDrawer(
        page.constraints,
        drawerContent = {
            DrawerHeader(drawerState, page.constraints)
            Divider()
            DrawerMenuItem(
                Icons.Default.Settings to "Settings",
                "アプリ設定",
                drawerState,
            ) {}
        },
        drawerState = drawerState,
        content = {
            when (page) {
                is Pages.SimpleCalculator -> SimpleCalculatorPage(
                    page.constraints.panel,
                    page.constraints.drawer,
                    drawerState,
                )
            }
        },
    )
}
