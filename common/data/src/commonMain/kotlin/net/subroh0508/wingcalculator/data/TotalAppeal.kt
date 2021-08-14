package net.subroh0508.wingcalculator.data

sealed class TotalAppeal {
    abstract val toVocal: Appeal.ToVocal
    abstract val toDance: Appeal.ToDance
    abstract val toVisual: Appeal.ToVisual

    data class Vocal(
        override val toVocal: Appeal.ToVocal,
        override val toDance: Appeal.ToDance,
        override val toVisual: Appeal.ToVisual,
    ) : TotalAppeal()

    data class Dance(
        override val toVocal: Appeal.ToVocal,
        override val toDance: Appeal.ToDance,
        override val toVisual: Appeal.ToVisual,
    ) : TotalAppeal()

    data class Visual(
        override val toVocal: Appeal.ToVocal,
        override val toDance: Appeal.ToDance,
        override val toVisual: Appeal.ToVisual,
    ) : TotalAppeal()
}
