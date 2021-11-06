package net.subroh0508.wingcalculator.producer.infra.repository

import net.subroh0508.wingcalculator.database.Producer as DBProducer
import net.subroh0508.wingcalculator.database.ProducerDatabase
import net.subroh0508.wingcalculator.producer.model.Producer

internal class ProducerRepositoryImpl(
    private val database: ProducerDatabase,
) : ProducerRepository {
    override fun getCurrent() = database.getCurrent().toEntity()

    override suspend fun fetchCurrent() = database.getCurrent().toEntity()

    private fun DBProducer.toEntity() = Producer(id, name?.takeIf(String::isNotBlank), isAnonymous)
}