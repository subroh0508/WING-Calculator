@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.templates

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.CollapsingTopAppBarContainer
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.TopAppSearchBar
import net.subroh0508.wingcalculator.composeui.components.molecules.appbar.TopAppSearchBarHeight
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.SimpleBuffForm
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.SimpleUnitForm

@Composable
fun SimpleCalculatorBackLayerContent(
    frontLayerHeight: Dp,
    onClickAppBarNavigation: () -> Unit,
) = SimpleCalculatorBoxWithConstraints { constraints ->
    CollapsingTopAppBarContainer(
        appBar = { appBarModifier ->
            TopAppSearchBar(
                null,
                onClickNavigation = onClickAppBarNavigation,
                onClickSearchBar = {},
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp,
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                modifier = appBarModifier.then(constraints),
            ) {
                IconButton(
                    onClick = {},
                    modifier = Modifier.padding(4.dp),
                ) {
                    Icon(
                        Icons.Default.Save,
                        contentDescription = "save",
                        modifier = Modifier.size(24.dp),
                    )
                }
            }
        },
        appBarHeight = TopAppSearchBarHeight,
    ) {
        SimpleUnitForm(constraints)
        Divider(constraints.padding(top = 24.dp, bottom = 16.dp, start = 8.dp, end = 8.dp))
        SimpleBuffForm(constraints)
        Spacer(Modifier.height(frontLayerHeight + 32.dp))
    }
}
