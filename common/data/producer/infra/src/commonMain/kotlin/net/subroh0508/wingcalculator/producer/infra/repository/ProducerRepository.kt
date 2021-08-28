package net.subroh0508.wingcalculator.producer.infra.repository

import net.subroh0508.wingcalculator.producer.model.Producer

interface ProducerRepository {
    suspend fun fetchCurrent(): Producer
}