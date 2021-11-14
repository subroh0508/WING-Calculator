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
import net.subroh0508.wingcalculator.composeui.pages.simple.Strings
import net.subroh0508.wingcalculator.composeui.pages.simple.TableTypePreferenceDispatcherContext
import net.subroh0508.wingcalculator.composeui.pages.simple.TableTypePreferenceProviderContext
import net.subroh0508.wingcalculator.composeui.pages.simple.getString
import net.subroh0508.wingcalculator.preference.model.AppPreference

@Composable
fun ColumnScope.CalculateResultHeader(
    headerHeight: Dp,
    isConcealed: Boolean? = null,
    onClickArrowIcon: () -> Unit = {},
) {
    val tableType = TableType.valueOf(TableTypePreferenceProviderContext.current)
    val dispatcher = TableTypePreferenceDispatcherContext.current

    FrontLayerHeader(
        getString(Strings.BackdropFrontLayerHeaderTitle),
        headerHeight,
        isConcealed,
        onClickArrowIcon = onClickArrowIcon,
        actions = {
            if (isConcealed != false)
                SettingsMenu(tableType) { dispatcher(it.toPreference()) }
        },
    )
    Divider(Modifier.padding(horizontal = 8.dp))
}

private enum class TableType(private val strings: Strings) : DropdownMenuItem {
    APPEAL(AppealType.LABEL), JUDGE(Judge.LABEL);

    fun toPreference() = AppPreference.Table.valueOf(name)

    override val label: String
        @Composable get() = getString(strings)

    companion object {
        fun valueOf(type: AppPreference.Table) = valueOf(type.name)
    }
}

@Composable
private fun SettingsMenu(
    type: TableType,
    onClick: (TableType) -> Unit,
) = ExpandableDropdownMenu(
    TableType.values().toList(),
    selected = type,
    onClick = onClick,
) {
    Icon(
        Icons.Default.Settings,
        contentDescription = "TableType",
    )
}

