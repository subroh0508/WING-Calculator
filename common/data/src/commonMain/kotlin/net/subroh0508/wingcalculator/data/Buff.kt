package net.subroh0508.wingcalculator.data

data class Buff(
    internal val ratio: List<Double>,
) {
    override fun toString() = ratio.joinToString(",") { (it * 100).toInt().toString() }

    internal fun sum() = ratio.sum()

    fun total() = "${(sum() * 100).toInt()}%"
}

data class Buffs(
    val forVocal: Buff,
    val forDance: Buff,
    val forVisual: Buff,
)

internal operator fun Double.times(buff: Buff) = (1.0 + buff.sum()) * this
