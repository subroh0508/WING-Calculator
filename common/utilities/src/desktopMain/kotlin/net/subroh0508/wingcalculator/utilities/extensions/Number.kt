@file:JvmName("DesktopNumber")

package net.subroh0508.wingcalculator.utilities.extensions

actual fun Float.toFixed(digits: Int): String = "%.${digits}f".format(this)
actual fun Double.toFixed(digits: Int): String = "%.${digits}f".format(this)
