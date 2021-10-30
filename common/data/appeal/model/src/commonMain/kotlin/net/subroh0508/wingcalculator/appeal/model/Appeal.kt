package net.subroh0508.wingcalculator.appeal.model

import net.subroh0508.wingcalculator.utilities.JvmInline

@JvmInline
value class Appeal(private val value: Int) { override fun toString() = value.toString() }