@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple

import androidx.compose.runtime.*
import net.subroh0508.wingcalculator.composeui.components.di.KoinComponentContainer
import net.subroh0508.wingcalculator.composeui.components.di.UiModelDispatcher
import net.subroh0508.wingcalculator.composeui.components.di.UiModelProvider
import net.subroh0508.wingcalculator.composeui.components.di.emptyUiModelDispatcher
import net.subroh0508.wingcalculator.composeui.components.themes.AppTheme
import net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers.provideFetchLatestModifiedPresetUseCase
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.composeui.pages.simple.templates.SimpleCalculatorDrawer
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
fun SimpleCalculatorPage() {
    AppTheme {
        KoinComponentContainer(
            SimpleCalculatorUiModel(),
            SimpleCalculatorDomainModule,
            SimpleCalculatorDispatcherContext,
            SimpleCalculatorProviderContext,
        ) { PageContent() }
    }
}

@Composable
private fun PageContent() {
    val (koin, _) = SimpleCalculatorProviderContext.current
    val (_, dispatch) = provideFetchLatestModifiedPresetUseCase()

    LaunchedEffect(koin) { dispatch() }

    SimpleCalculatorDrawer()
}
