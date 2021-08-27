package net.subroh0508.wingcalculator.preset.model

import net.subroh0508.wingcalculator.appeal.model.Idol

data class Preset(
    val name: String,
    val pIdol: Idol.Produce,
    val sIdols: List<Idol.Support>,
    val comment: String,
) {
    internal constructor(name: String, idols: List<Idol>, comment: String) : this(
        name,
        idols.filterIsInstance<Idol.Produce>().first(),
        idols.filterIsInstance<Idol.Support>(),
        comment,
    )
}
