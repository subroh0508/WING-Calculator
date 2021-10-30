@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.appframe

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import net.subroh0508.wingcalculator.composeui.appframe.constraints.panel
import net.subroh0508.wingcalculator.composeui.appframe.menu.DrawerHeader
import net.subroh0508.wingcalculator.composeui.appframe.menu.DrawerMenuItem
import net.subroh0508.wingcalculator.composeui.appframe.preference.AppPreferencePage
import net.subroh0508.wingcalculator.composeui.components.di.getKoin
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.ResponsiveDrawer
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.ResponsiveDrawerState
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.rememberResponsiveDrawerState
import net.subroh0508.wingcalculator.composeui.components.themes.AppTheme
import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorPage
import net.subroh0508.wingcalculator.preference.model.AppPreference
import net.subroh0508.wingcalculator.usecase.preference.FetchAppPreferenceUseCase
import net.subroh0508.wingcalculator.usecase.preference.UpdateAppPreferenceUseCase

typealias AppPreferenceDispatcher = (AppPreference) -> Unit

val AppPreferenceDispatcherContext = compositionLocalOf<AppPreferenceDispatcher>(defaultFactory = { {} })

@Composable
fun AppFrame() = BoxWithConstraints {
    val (page, controller) = providePageController(maxWidth)
    val drawerState = rememberResponsiveDrawerState(page.constraints, DrawerValue.Closed)

    val koin = getKoin()
    val scope = rememberCoroutineScope()
    val fetchAppPreferenceUseCase: FetchAppPreferenceUseCase = remember(koin) { koin.get() }
    val updateAppPreferenceUseCase: UpdateAppPreferenceUseCase = remember(koin) { koin.get() }

    var preference: AppPreference by remember(koin) { mutableStateOf(AppPreference()) }

    LaunchedEffect(koin) { preference = fetchAppPreferenceUseCase.execute() }

    CompositionLocalProvider(
        AppPreferenceDispatcherContext provides {
            scope.launch { preference = updateAppPreferenceUseCase.execute(it) }
        },
    ) {
        ThemedAppFrame(preference, drawerState, page, controller)
    }
}

@Composable
private fun ThemedAppFrame(
    preference: AppPreference,
    drawerState: ResponsiveDrawerState,
    page: Pages,
    controller: PageController,
) = AppTheme(
    darkTheme = if (preference.theme == AppPreference.Theme.SYSTEM)
                    isSystemInDarkTheme()
                else
                    preference.theme == AppPreference.Theme.DARK,
) {
    val dispatcher = AppPreferenceDispatcherContext.current

    ResponsiveDrawer(
        page.constraints,
        drawerContent = {
            DrawerHeader(drawerState, page.constraints)
            Divider()
            DrawerMenuItem(
                Icons.Default.Calculate to "SimpleCalculator",
                "アピール値計算",
                drawerState,
                controller::openSimpleCalculator,
            )
            DrawerMenuItem(
                Icons.Default.Settings to "Settings",
                "アプリ設定",
                drawerState,
                controller::openAppPreference,
            )
        },
        drawerState = drawerState,
        content = {
            when (page) {
                is Pages.SimpleCalculator -> SimpleCalculatorPage(
                    page.constraints.panel,
                    page.constraints.drawer,
                    drawerState,
                    preference.table,
                ) { dispatcher(preference.copy(table = it)) }
                is Pages.AppPreference -> AppPreferencePage(
                    page.constraints.drawer,
                    preference,
                    drawerState,
                )
            }
        },
    )
}
