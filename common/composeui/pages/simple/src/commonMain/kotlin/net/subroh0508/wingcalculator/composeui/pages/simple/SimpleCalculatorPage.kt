@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import net.subroh0508.wingcalculator.composeui.components.di.*
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.ResponsibleDrawerState
import net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers.provideFetchLatestModifiedPresetUseCase
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
    panelsCount: Int,
    drawerState: ResponsibleDrawerState,
) = KoinComponentContainer(
    SimpleCalculatorUiModel(panelsCount = panelsCount),
    SimpleCalculatorDomainModule,
    SimpleCalculatorDispatcherContext,
    SimpleCalculatorProviderContext,
) { PageContent(panelsCount, drawerState) }


@Composable
private fun PageContent(
    panelsCount: Int,
    drawerState: ResponsibleDrawerState,
) {
    val (koin, _) = SimpleCalculatorProviderContext.current
    val (_, dispatch) = provideFetchLatestModifiedPresetUseCase()

    LaunchedEffect(koin) { dispatch() }

    if (panelsCount == 1) {
        SimpleCalculatorBackdrop(drawerState)
        return
    }

    Row(Modifier.background(color = MaterialTheme.colors.background)) {
        BackLayerContent(modifier = Modifier.weight(1F))
        FrontLayerContent(modifier = Modifier.weight(1F))
    }
}

