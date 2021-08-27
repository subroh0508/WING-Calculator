package net.subroh0508.wingcalculator.preset.model

import net.subroh0508.wingcalculator.appeal.model.Idol

data class Preset(
    val id: Long,
    val name: String,
    val pIdol: Idol.Produce,
    val sIdols: List<Idol.Support>,
    val comment: String?,
)
