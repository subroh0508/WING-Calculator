package net.subroh0508.wingcalculator.composeui.components

enum class CommonStrings {
    Cancel,
    Edit,
    Save,
    Close,
}

expect fun getString(string: CommonStrings): String
