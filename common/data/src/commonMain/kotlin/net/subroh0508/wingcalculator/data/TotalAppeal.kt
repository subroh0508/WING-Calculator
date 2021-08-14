package net.subroh0508.wingcalculator.data

sealed class TotalAppeal {
    abstract val toVocal: Appeal.ToVocal
    abstract val toDance: Appeal.ToDance
    abstract val toVisual: Appeal.ToVisual

    data class Vocal(
        override val toVocal: Appeal.ToVocal = Appeal.ToVocal(0),
        override val toDance: Appeal.ToDance = Appeal.ToDance(0),
        override val toVisual: Appeal.ToVisual = Appeal.ToVisual(0),
    ) : TotalAppeal()

    data class Dance(
        override val toVocal: Appeal.ToVocal = Appeal.ToVocal(0),
        override val toDance: Appeal.ToDance = Appeal.ToDance(0),
        override val toVisual: Appeal.ToVisual = Appeal.ToVisual(0),
    ) : TotalAppeal()

    data class Visual(
        override val toVocal: Appeal.ToVocal = Appeal.ToVocal(0),
        override val toDance: Appeal.ToDance = Appeal.ToDance(0),
        override val toVisual: Appeal.ToVisual = Appeal.ToVisual(0),
    ) : TotalAppeal()
}
