package net.subroh0508.wingcalculator.data

sealed interface Status

@JvmInline
value class Vocal(internal val value: Int) : Status { operator fun plus(s: Vocal) = Vocal(value + s.value) }
@JvmInline
value class Dance(internal val value: Int) : Status { operator fun plus(s: Dance) = Dance(value + s.value) }
@JvmInline
value class Visual(internal val value: Int) : Status { operator fun plus(s: Visual) = Visual(value + s.value) }

internal fun List<Status>.sum() = fold(0.0) { acc, s ->
    acc + when (s) {
        is Vocal -> s.value
        is Dance -> s.value
        is Visual -> s.value
    }
}

internal operator fun Status.times(factor: Double) = when (this) {
    is Vocal -> value * factor
    is Dance -> value * factor
    is Visual -> value * factor
}
