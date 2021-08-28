package net.subroh0508.wingcalculator.preset.infra.repository

import net.subroh0508.wingcalculator.appeal.model.Dance
import net.subroh0508.wingcalculator.appeal.model.Idol
import net.subroh0508.wingcalculator.appeal.model.Visual
import net.subroh0508.wingcalculator.appeal.model.Vocal
import net.subroh0508.wingcalculator.database.PresetForm
import net.subroh0508.wingcalculator.database.PresetDatabase
import net.subroh0508.wingcalculator.preset.model.Preset

internal class PresetRepositoryImpl(
    private val database: PresetDatabase,
) : PresetRepository {
    override suspend fun fetch(id: Long, producerId: Long) = database.get(id, producerId).toEntity()
    override suspend fun search(name: String, producerId: Long) = database.search(producerId, name).map { it.toEntity() }

    override suspend fun save(
        producerId: Long,
        name: String,
        pIdol: Idol.Produce,
        sIdols: List<Idol.Support>,
        comment: String?
    ) = database.create(
        producerId,
        name,
        pIdol.toList().map(Int::toLong),
        sIdols.map { sIdol -> sIdol.toList().map(Int::toLong) },
        comment,
    ).toEntity()

    override suspend fun save(
        id: Long,
        producerId: Long,
        name: String,
        pIdol: Idol.Produce,
        sIdols: List<Idol.Support>,
        comment: String?
    ) = database.update(
        id,
        producerId,
        name,
        pIdol.toList().map(Int::toLong),
        sIdols.map { sIdol -> sIdol.toList().map(Int::toLong) },
        comment,
    ).toEntity()

    private fun PresetForm.toEntity() = Preset(
        id,
        name,
        Idol.Produce(Vocal(pVocal.toInt()), Dance(pDance.toInt()), Visual(pVisual.toInt())),
        listOf(
            Idol.Support(Vocal(s1Vocal.toInt()), Dance(s1Dance.toInt()), Visual(s1Visual.toInt())),
            Idol.Support(Vocal(s2Vocal.toInt()), Dance(s2Dance.toInt()), Visual(s2Visual.toInt())),
            Idol.Support(Vocal(s3Vocal.toInt()), Dance(s3Dance.toInt()), Visual(s3Visual.toInt())),
            Idol.Support(Vocal(s4Vocal.toInt()), Dance(s4Dance.toInt()), Visual(s4Visual.toInt())),
        ),
        comment,
    )
}