package net.subroh0508.wingcalculator.composeui.components

import androidx.compose.runtime.Composable

@Composable
internal actual fun getString(string: Strings): String = when (string) {
    Strings.CloseDrawer -> "Close navigation menu"
    Strings.IconDescriptionBackdropReveal -> "reveal"
    Strings.IconDescriptionBackdropConceal -> "conceal"
    Strings.IconDescriptionDeletableListItem -> "delete"
    Strings.IconDescriptionTopAppBarNavigation -> "navigation"
    Strings.IconDescriptionOpenedSearchBarBack -> "back"
    Strings.IconDescriptionOpenedSearchBarClear -> "clear"
    Strings.IconDescriptionDropdownSelector -> "select"
    Strings.IconDescriptionSwitcherStart -> "back"
    Strings.IconDescriptionSwitcherEnd -> "forward"
}
