package net.subroh0508.wingcalculator.producer.infra.di

import net.subroh0508.wingcalculator.database.di.ProducerDatabases
import net.subroh0508.wingcalculator.producer.infra.repository.ProducerRepository
import net.subroh0508.wingcalculator.producer.infra.repository.ProducerRepositoryImpl
import org.koin.dsl.module

object ProducerRepositories {
    val Module get() = ProducerDatabases.Module + module {
        single<ProducerRepository> { ProducerRepositoryImpl(get()) }
    }
}