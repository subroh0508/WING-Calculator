package net.subroh0508.wingcalculator.preference.model

data class AppPreference(
    val theme: Theme = Theme.SYSTEM,
    val table: Table = Table.APPEAL,
) {
    enum class Theme { DARK, LIGHT, SYSTEM }
    enum class Table { APPEAL, JUDGE }
}
