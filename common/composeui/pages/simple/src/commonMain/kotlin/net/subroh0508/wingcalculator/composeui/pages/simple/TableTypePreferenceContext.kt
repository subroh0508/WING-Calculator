@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import net.subroh0508.wingcalculator.preference.model.AppPreference

typealias TableTypePreferenceDispatcher = (AppPreference.Table) -> Unit

val TableTypePreferenceDispatcherContext = compositionLocalOf<TableTypePreferenceDispatcher>(
    defaultFactory = { {} }
)
val TableTypePreferenceProviderContext = compositionLocalOf(
    defaultFactory = { AppPreference.Table.JUDGE }
)

@Composable
fun TableTypePreferenceContext(
    table: AppPreference.Table,
    dispatcher: (AppPreference.Table) -> Unit,
    content: @Composable () -> Unit,
) = CompositionLocalProvider(
    TableTypePreferenceDispatcherContext provides dispatcher,
    TableTypePreferenceProviderContext provides table,
    content = content,
)
