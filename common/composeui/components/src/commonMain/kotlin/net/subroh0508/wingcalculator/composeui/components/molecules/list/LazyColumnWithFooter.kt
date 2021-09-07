@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.molecules.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T: Any> LazyColumnWithFooter(
    items: List<T>,
    modifier: Modifier = Modifier,
    footer: @Composable LazyItemScope.() -> Unit = {
        Spacer(modifier = Modifier.height(120.dp))
    },
    item: @Composable LazyItemScope.(Int, T) -> Unit,
) = LazyColumn(modifier = modifier) {
    items.forEachIndexed { index, t -> item(index) { item(index, t) } }

    item(items.size, footer)
}
