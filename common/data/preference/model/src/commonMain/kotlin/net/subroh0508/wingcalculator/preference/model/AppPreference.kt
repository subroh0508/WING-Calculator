package net.subroh0508.wingcalculator.preference.model

data class AppPreference(
        val theme: Theme,
        val table: Table,
) {
    enum class Theme { DARK, LIGHT, SYSTEM }
    enum class Table { APPEAL, JUDGE }
}
