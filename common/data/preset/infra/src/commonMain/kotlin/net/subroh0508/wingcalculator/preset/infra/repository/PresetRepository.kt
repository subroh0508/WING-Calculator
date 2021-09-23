package net.subroh0508.wingcalculator.preset.infra.repository

import net.subroh0508.wingcalculator.appeal.model.Idol
import net.subroh0508.wingcalculator.preset.model.Preset

interface PresetRepository {
    suspend fun fetch(id: Long, producerId: Long): Preset
    suspend fun fetchBlankName(producerId: Long): Preset?
    suspend fun search(name: String?, producerId: Long): List<Preset>
    suspend fun save(id: Long, producerId: Long, name: String, pIdol: Idol.Produce, sIdols: List<Idol.Support>, comment: String? = null): Preset
    suspend fun save(producerId: Long, name: String, pIdol: Idol.Produce, sIdols: List<Idol.Support>, comment: String? = null): Preset
    suspend fun delete(id: Long, producerId: Long)
}