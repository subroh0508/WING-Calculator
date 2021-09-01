package net.subroh0508.wingcalculator.appeal.model

sealed class BaseAppeal {
    internal abstract val pStatus: Status
    internal abstract val sStatus: List<Status>
    internal abstract val week: Week

    protected abstract val base: Double
    protected val weekRatio get() = 0.2 * (1.0 + week * 0.1)

    operator fun times(buff: Buff) = base * buff

    data class Produce(
        override val pStatus: Status,
        override val sStatus: List<Status>,
        override val week: Week,
    ) : BaseAppeal() {
        override val base = pStatus * 2.0 + sStatus.sum() * weekRatio
    }

    data class Support(
        override val sStatus: List<Status>,
        override val pStatus: Status,
        override val week: Week,
    ) : BaseAppeal() {
        override val base = pStatus * 0.5 + (sStatus.sum() + sStatus.first() * 3.0) * weekRatio
    }
}