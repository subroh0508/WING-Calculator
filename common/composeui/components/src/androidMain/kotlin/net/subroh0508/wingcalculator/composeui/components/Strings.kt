package net.subroh0508.wingcalculator.composeui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import net.subroh0508.wingcalculator.composeui.components.R

@Composable
internal actual fun getString(string: Strings): String {
    val resources = LocalContext.current.resources

    return when (string) {
        Strings.IconDescriptionBackdropReveal -> resources.getString(R.string.icon_description_backdrop_reveal)
        Strings.IconDescriptionBackdropConceal -> resources.getString(R.string.icon_description_backdrop_conceal)
        Strings.IconDescriptionDeletableListItem -> resources.getString(R.string.icon_description_deletable_list_item)
        Strings.IconDescriptionTopAppBarNavigation -> resources.getString(R.string.icon_description_top_app_bar_navigation)
        Strings.IconDescriptionOpenedSearchBarBack -> resources.getString(R.string.icon_description_opened_search_bar_back)
        Strings.IconDescriptionOpenedSearchBarClear -> resources.getString(R.string.icon_description_opened_search_bar_clear)
        Strings.IconDescriptionDropdownSelector -> resources.getString(R.string.icon_description_dropdown_selector)
        Strings.IconDescriptionSwitcherStart -> resources.getString(R.string.icon_description_switcher_start)
        Strings.IconDescriptionSwitcherEnd -> resources.getString(R.string.icon_description_switcher_end)
    }
}
