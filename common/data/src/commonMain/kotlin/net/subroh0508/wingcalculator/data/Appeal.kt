package net.subroh0508.wingcalculator.data

sealed class Appeal {
    abstract val value: Int

    data class ToVocal(override val value: Int) : Appeal()
    data class ToDance(override val value: Int) : Appeal()
    data class ToVisual(override val value: Int) : Appeal()
}