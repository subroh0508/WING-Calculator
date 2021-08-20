package net.subroh0508.wingcalculator.data

sealed class BaseAppeal {
    abstract operator fun times(buff: Buff): Double

    data class Produce(
        val pStatus: Status,
        val sStatus: List<Status>,
        val week: Week,
    ) : BaseAppeal() {
        private val base = pStatus * 2.0 + sStatus.sum() * 0.2 * (1.0 + week * 0.1)

        override operator fun times(buff: Buff) = base * buff
    }

    data class Support(
        val sStatus: List<Status>,
        val pStatus: Status,
        val week: Week,
    ) : BaseAppeal() {
        private val base = pStatus * 0.5 + (sStatus.sum() + sStatus.first() * 3.0) * 0.2 * (1.0 + week * 0.1)

        override operator fun times(buff: Buff) = base * buff
    }
}