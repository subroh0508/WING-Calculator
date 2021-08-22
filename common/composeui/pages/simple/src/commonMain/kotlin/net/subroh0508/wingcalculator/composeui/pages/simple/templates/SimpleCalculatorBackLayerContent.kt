@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.CollapsingTopAppBarContainer
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.SimpleBuffForm
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.SimpleUnitForm

@Composable
fun SimpleCalculatorBackLayerContent(
    frontLayerHeight: Dp,
    onClickAppBarNavigation: () -> Unit,
) = SimpleCalculatorBoxWithConstraints { constraints ->
    CollapsingTopAppBarContainer(
        onClickAppBarNavigation = onClickAppBarNavigation,
        appBarBackgroundColor = MaterialTheme.colors.background,
        appBarElevation = 0.dp,
        appBarActions = {
            IconButton(onClick = {}) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "search",
                )
            }
        }
    ) {
        SimpleUnitForm(constraints)
        Divider(constraints.padding(top = 24.dp, bottom = 16.dp, start = 8.dp, end = 8.dp))
        SimpleBuffForm(constraints)
        Spacer(Modifier.height(frontLayerHeight + 32.dp))
    }
}
