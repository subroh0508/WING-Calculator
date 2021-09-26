@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.borderEnd
import net.subroh0508.wingcalculator.composeui.components.di.*
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.DrawerType
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.ResponsiveDrawerState
import net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers.provideLoadSimpleCalculatorUiModelDispatcher
import net.subroh0508.wingcalculator.composeui.pages.simple.model.Panels
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.composeui.pages.simple.templates.SimpleCalculatorBackdrop
import net.subroh0508.wingcalculator.composeui.pages.simple.templates.backdrop.BackLayerContent
import net.subroh0508.wingcalculator.composeui.pages.simple.templates.backdrop.FrontLayerContent
import net.subroh0508.wingcalculator.usecase.simple.di.SimpleCalculatorDomainModule

typealias SimpleCalculatorDispatcher = UiModelDispatcher<SimpleCalculatorUiModel>
typealias SimpleCalculatorProvider = UiModelProvider<SimpleCalculatorUiModel>

val SimpleCalculatorDispatcherContext = compositionLocalOf<SimpleCalculatorDispatcher>(
    defaultFactory = { ::emptyUiModelDispatcher },
)
val SimpleCalculatorProviderContext = compositionLocalOf(
    defaultFactory = { SimpleCalculatorProvider(null, SimpleCalculatorUiModel()) },
)

@Composable
fun SimpleCalculatorPage(
    panel: Panels,
    drawer: DrawerType,
    drawerState: ResponsiveDrawerState,
) = KoinComponentContainer(
    SimpleCalculatorUiModel(panel = panel, drawer = drawer),
    SimpleCalculatorDomainModule,
    SimpleCalculatorDispatcherContext,
    SimpleCalculatorProviderContext,
) { PageContent(panel, drawer, drawerState) }


@Composable
private fun PageContent(
    panel: Panels,
    drawer: DrawerType,
    drawerState: ResponsiveDrawerState,
) {
    val koin = SimpleCalculatorProviderContext.current.koin
    val (uiModel, dispatch) = provideLoadSimpleCalculatorUiModelDispatcher()

    LaunchedEffect(koin, panel, drawer) { dispatch(panel, drawer) }

    when (uiModel.panel) {
        Panels.ONE -> SimpleCalculatorBackdrop(drawerState)
        Panels.TWO -> Row(Modifier.background(color = MaterialTheme.colors.background)) {
            BackLayerContent(
                modifier = Modifier.weight(1F)
                    .borderEnd(1.dp),
            )
            FrontLayerContent(
                modifier = Modifier.weight(1F)
                    .borderEnd(1.dp),
            )
        }
    }
}

@Composable
private fun Modifier.borderEnd(width: Dp) = borderEnd(
    MaterialTheme.colors.onSurface.copy(alpha = 0.12F),
    with (LocalDensity.current) { width.toPx() },
)
