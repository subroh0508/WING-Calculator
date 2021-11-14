package net.subroh0508.wingcalculator.composeui.appframe

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
internal actual fun getString(string: Strings): String {
    val resources = LocalContext.current.resources

    return when (string) {
        Strings.PageTitleSimpleCalculator -> resources.getString(R.string.page_title_simple_calculator)
        Strings.PageTitlePreference -> resources.getString(R.string.page_title_preference)
        Strings.PreferenceColorThemeLabel -> resources.getString(R.string.preference_color_theme_label)
        Strings.PreferenceColorThemeSystem -> resources.getString(R.string.preference_color_theme_system)
        Strings.PreferenceColorThemeLight -> resources.getString(R.string.preference_color_theme_light)
        Strings.PreferenceColorThemeDark -> resources.getString(R.string.preference_color_theme_dark)
        Strings.IconDescriptionSimpleCalculator -> resources.getString(R.string.app_frame_icon_description_simple_calculator)
        Strings.IconDescriptionPreference -> resources.getString(R.string.app_frame_icon_description_preference)
    }
}
