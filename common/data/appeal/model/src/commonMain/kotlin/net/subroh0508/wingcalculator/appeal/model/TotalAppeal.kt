@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.appeal.model

typealias TotalAppeal = List<Appeal>

fun TotalAppeal(
    vocal: Appeal = Appeal(0),
    dance: Appeal = Appeal(0),
    visual: Appeal = Appeal(0),
) = listOf(vocal, dance, visual)
