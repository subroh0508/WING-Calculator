package net.subroh0508.wingcalculator.appeal.model

sealed class Idol {
    abstract val vocal: Vocal
    abstract val dance: Dance
    abstract val visual: Visual

    open fun toList() = listOf(vocal.value, dance.value, visual.value)

    data class Produce(
        override val vocal: Vocal = Vocal(0),
        override val dance: Dance = Dance(0),
        override val visual: Visual = Visual(0),
    ) : Idol()

    data class Support(
        override val vocal: Vocal = Vocal(0),
        override val dance: Dance = Dance(0),
        override val visual: Visual = Visual(0),
    ) : Idol()
}
