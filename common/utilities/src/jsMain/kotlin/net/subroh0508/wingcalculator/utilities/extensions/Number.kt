package net.subroh0508.wingcalculator.utilities.extensions

actual fun Float.toFixed(digits: Int): String = asDynamic().toFixed(digits) as String
actual fun Double.toFixed(digits: Int): String = asDynamic().toFixed(digits) as String
