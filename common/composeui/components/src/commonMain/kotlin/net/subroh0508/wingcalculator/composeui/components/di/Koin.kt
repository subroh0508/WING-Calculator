package net.subroh0508.wingcalculator.composeui.components.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.core.Koin
import org.koin.core.context.GlobalContext
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

@Composable
inline fun <reified T> get(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null,
): T = remember(qualifier, parameters) {
    GlobalContext.get().get(qualifier, parameters)
}

@Composable
fun getKoin(): Koin = remember(GlobalContext::get)
