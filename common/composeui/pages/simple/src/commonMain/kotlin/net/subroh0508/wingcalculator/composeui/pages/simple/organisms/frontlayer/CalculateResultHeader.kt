@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms.frontlayer

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.components.atoms.backdrop.FrontLayerHeader
import net.subroh0508.wingcalculator.composeui.components.molecules.menu.DropdownMenuItem
import net.subroh0508.wingcalculator.composeui.components.molecules.menu.ExpandableDropdownMenu
import net.subroh0508.wingcalculator.composeui.pages.simple.TableTypePreferenceDispatcherContext
import net.subroh0508.wingcalculator.preference.model.AppPreference

@Composable
fun ColumnScope.CalculateResultHeader(
    headerHeight: Dp,
    isConcealed: Boolean? = null,
    onClickArrowIcon: () -> Unit = {},
) {
    val dispatcher = TableTypePreferenceDispatcherContext.current

    FrontLayerHeader(
        "計算結果",
        headerHeight,
        isConcealed,
        onClickArrowIcon = onClickArrowIcon,
        actions = { if (isConcealed != false) SettingsMenu { dispatcher(it.toPreference()) } },
    )
    Divider(Modifier.padding(horizontal = 8.dp))
}

private enum class TableType(override val label: String) : DropdownMenuItem {
    APPEAL_TYPE(AppealType.LABEL), JUDGE(Judge.LABEL);

    fun toPreference() = when (this) {
        APPEAL_TYPE -> AppPreference.Table.APPEAL
        JUDGE -> AppPreference.Table.JUDGE
    }
}

@Composable
private fun SettingsMenu(
    onClick: (TableType) -> Unit,
) = ExpandableDropdownMenu(
    TableType.values().toList(),
    onClick = onClick,
) {
    Icon(
        Icons.Default.Settings,
        contentDescription = "TableType",
    )
}

