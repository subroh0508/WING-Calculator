package net.subroh0508.wingcalculator.data

sealed class Idol {
    abstract val vocal: Vocal
    abstract val dance: Dance
    abstract val visual: Visual

    data class Produce(
        override val vocal: Vocal,
        override val dance: Dance,
        override val visual: Visual,
    ) : Idol()

    data class Support(
        override val vocal: Vocal,
        override val dance: Dance,
        override val visual: Visual,
    ) : Idol()
}
