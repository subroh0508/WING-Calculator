@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.di

import androidx.compose.runtime.*
import org.koin.core.module.Module

@Composable
fun KoinComponent(
    module: List<Module>,
    content: @Composable () -> Unit,
) {
    val koin = getKoin()

    var koinLoadState by remember { mutableStateOf(false) }

    DisposableEffect(koin) {
        koin.loadModules(module)
        koinLoadState = true

        onDispose {
            koin.unloadModules(module)
        }
    }

    if (!koinLoadState) {
        return
    }

    content()
}
