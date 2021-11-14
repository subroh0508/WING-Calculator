package net.subroh0508.wingcalculator.composeui.appframe

import androidx.compose.runtime.Composable

@Composable
internal actual fun getString(string: Strings) = when (string) {
    Strings.PageTitleSimpleCalculator -> "アピール値計算"
    Strings.PageTitlePreference -> "アプリ設定"
    Strings.PreferenceColorThemeLabel -> "モード"
    Strings.PreferenceColorThemeSystem -> "システム設定に従う"
    Strings.PreferenceColorThemeLight -> "ライトモード"
    Strings.PreferenceColorThemeDark -> "ダークモード"
    Strings.IconDescriptionSimpleCalculator -> "SimpleCalculator"
    Strings.IconDescriptionPreference -> "Settings"
}
