package net.subroh0508.wingcalculator.composeui.appframe

internal enum class Strings {
    PageTitleSimpleCalculator,
    PageTitlePreference,
    PreferenceColorThemeLabel,
    PreferenceColorThemeSystem,
    PreferenceColorThemeLight,
    PreferenceColorThemeDark,

    IconDescriptionSimpleCalculator,
    IconDescriptionPreference,
}

internal expect fun getString(string: Strings): String
