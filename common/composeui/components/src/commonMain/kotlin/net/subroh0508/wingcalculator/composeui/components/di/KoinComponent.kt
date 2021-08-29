@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.di

import androidx.compose.runtime.*
import org.koin.core.Koin
import org.koin.core.module.Module

@Composable
fun KoinComponent(
    module: List<Module>,
    content: @Composable (Koin?) -> Unit,
) {
    val koin = getKoin()
    var koinLoadState: Koin? by remember { mutableStateOf(null) }

    DisposableEffect(koin) {
        koin.loadModules(module)
        koinLoadState = koin

        onDispose { koin.unloadModules(module) }
    }

    content(koinLoadState)
}
