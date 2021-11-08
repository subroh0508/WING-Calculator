package net.subroh0508.wingcalculator.appeal.infra.repository

import net.subroh0508.wingcalculator.appeal.model.BuffForm

interface BuffFormRepository {
    fun get(): BuffForm
    fun save(form: BuffForm): BuffForm
}
