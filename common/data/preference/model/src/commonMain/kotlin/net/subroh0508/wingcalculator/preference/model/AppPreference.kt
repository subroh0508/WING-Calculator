package net.subroh0508.wingcalculator.preference.model

data class AppPreference(
    val theme: Theme = Theme.SYSTEM,
    val table: Table = Table.APPEAL,
) {
    enum class Theme { SYSTEM, LIGHT, DARK }
    enum class Table { APPEAL, JUDGE }
}
