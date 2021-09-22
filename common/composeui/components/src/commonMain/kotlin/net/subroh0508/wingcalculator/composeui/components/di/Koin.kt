package net.subroh0508.wingcalculator.composeui.components.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.core.Koin
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.mp.KoinPlatformTools

@Composable
inline fun <reified T> get(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null,
): T = remember(qualifier, parameters) {
    KoinPlatformTools.defaultContext().get().get(qualifier, parameters)
}

@Composable
fun getKoin(): Koin = remember(KoinPlatformTools.defaultContext()::get)
