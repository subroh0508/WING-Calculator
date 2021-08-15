package net.subroh0508.wingcalculator.data

sealed class BaseAppeal {
    data class Produce(
        val pStatus: Status,
        val sStatus: List<Status>,
        val week: Week,
    ) {
        private val base = pStatus * 2.0 + sStatus.sum() * 0.2 * (1.0 + week * 0.1)

        operator fun times(buff: Buff) = base * buff
    }
}