package net.subroh0508.wingcalculator.composeui.components

import androidx.compose.runtime.Composable

@Composable
internal actual fun getString(string: Strings): String = when (string) {
    Strings.CloseDrawer -> "Close navigation menu"
    else -> ""
}
