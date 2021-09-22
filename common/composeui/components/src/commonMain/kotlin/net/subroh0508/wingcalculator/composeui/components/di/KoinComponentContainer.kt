@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.di

import androidx.compose.runtime.*
import org.koin.core.Koin
import org.koin.core.module.Module

typealias UiModelDispatcher<T> = (T) -> Unit
typealias UiModelProvider<T> = Pair<Koin?, T>

fun <T> emptyUiModelDispatcher(model: T) = Unit
val UiModelProvider<*>.koin get() = first
val <T> UiModelProvider<T>.uiModel get() = second

@Composable
fun <T: Any> KoinComponentContainer(
    initUiModel: T,
    module: List<Module>,
    dispatcherContext: ProvidableCompositionLocal<UiModelDispatcher<T>>,
    providerContext: ProvidableCompositionLocal<UiModelProvider<T>>,
    content: @Composable () -> Unit,
) {
    var uiModel by remember { mutableStateOf(initUiModel) }

    KoinComponent(module) {
        CompositionLocalProvider(
            dispatcherContext provides { uiModel = it },
        ) {
            CompositionLocalProvider(
                providerContext provides (it to uiModel),
                content = content,
            )
        }
    }
}
