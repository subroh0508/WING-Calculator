package net.subroh0508.wingcalculator.composeui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import net.subroh0508.wingcalculator.composeui.components.R

@Composable
internal actual fun getString(string: Strings): String {
    val resources = LocalContext.current.resources

    return when (string) {
        Strings.CloseDrawer -> resources.getString(R.string.close_drawer)
        else -> ""
    }
}
