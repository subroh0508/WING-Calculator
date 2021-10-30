package net.subroh0508.wingcalculator.appeal.model

import net.subroh0508.wingcalculator.utilities.JvmInline

@JvmInline
value class Appeal(private val value: Int) {
    operator fun plus(other: Appeal) = Appeal(value + other.value)

    override fun toString() = value.toString()
}