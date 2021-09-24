package net.subroh0508.wingcalculator.composeui.components

import net.subroh0508.wingcalculator.utilities.JvmInline

@JvmInline
internal value class Strings private constructor(private val value: Int) {
    companion object {
        val CloseDrawer = Strings(0)
    }
}

internal expect fun getString(string: Strings): String
