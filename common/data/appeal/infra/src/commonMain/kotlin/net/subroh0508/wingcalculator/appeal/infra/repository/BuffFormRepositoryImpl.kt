package net.subroh0508.wingcalculator.appeal.infra.repository

import net.subroh0508.wingcalculator.appeal.model.BuffForm
import net.subroh0508.wingcalculator.appeal.model.buffform.*
import net.subroh0508.wingcalculator.database.LatestBuffForm
import net.subroh0508.wingcalculator.database.LatestBuffFormDatabase

internal class BuffFormRepositoryImpl(
    private val database: LatestBuffFormDatabase,
) : BuffFormRepository {
    override fun get() = database.get().toValueObject()
    override fun save(form: BuffForm): BuffForm {
        database.update(
            form.week.week.toLong(),
            form.week.season.name,
            form.buffs.forVocal.toString(),
            form.buffs.forDance.toString(),
            form.buffs.forVisual.toString(),
            form.appealRatio.ratio,
            form.appealJudge.factor.name,
            form.interestRatio.toString(),
            form.memoryLevel.name,
        )

        return form
    }

    private fun LatestBuffForm.toValueObject() = BuffForm(
        Week(Week.Season.valueOf(season), (week ?: 1).toInt()),
        AppealRatio(appealRatio),
        Buffs(
            Buff(vocalBuff) ?: Buff(),
            Buff(danceBuff) ?: Buff(),
            Buff(visualBuff) ?: Buff(),
        ),
        AppealJudge(AppealJudge.Factor.valueOf(appealJudge)),
        InterestRatio(interestRatio.split(",").map(String::toDouble)),
        MemoryLevel.valueOf(memoryLevel),
    )
}
