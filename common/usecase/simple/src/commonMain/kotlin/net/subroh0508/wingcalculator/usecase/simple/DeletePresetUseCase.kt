package net.subroh0508.wingcalculator.usecase.simple

interface DeletePresetUseCase {
    suspend fun execute(id: Long)
}