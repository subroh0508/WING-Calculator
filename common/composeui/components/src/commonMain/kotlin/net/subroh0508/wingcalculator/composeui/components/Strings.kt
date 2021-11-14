package net.subroh0508.wingcalculator.composeui.components

internal enum class Strings {
    CloseDrawer,
    IconDescriptionBackdropReveal,
    IconDescriptionBackdropConceal,
    IconDescriptionDeletableListItem,
    IconDescriptionTopAppBarNavigation,
    IconDescriptionOpenedSearchBarBack,
    IconDescriptionOpenedSearchBarClear,
    IconDescriptionDropdownSelector,
    IconDescriptionSwitcherStart,
    IconDescriptionSwitcherEnd;
}

internal expect fun getString(string: Strings): String
