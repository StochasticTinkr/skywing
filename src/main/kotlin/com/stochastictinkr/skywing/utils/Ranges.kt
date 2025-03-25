package com.stochastictinkr.skywing.utils

typealias Range = ClosedFloatingPointRange<Double>

fun IntRange.toDoubleRange() = start.toDouble()..endInclusive.toDouble()

val Range.delta get() = endInclusive - start

/**
 * A scaling function that maps a value from one range to another.
 */
data class Scaling(
    val input: Range,
    val output: Range,
) : (Double) -> Double {
    val scale = output.delta / input.delta

    /**
     * The inverse of this scaling function.
     */
    val reversed get() = Scaling(output, input)

    /**
     * Scale the value from the input range to the output range.
     */
    override operator fun invoke(value: Double) = (value - input.start) * scale + output.start

    /**
     * Scale the value from the output range to the input range.
     */
    fun reversed(value: Double) = (value - output.start) / scale + input.start
}

infix fun Range.scalingTo(other: Range) = Scaling(this, other)
infix fun IntRange.scalingTo(other: Range) = Scaling(this.toDoubleRange(), other)

fun Scaling.asConverter() = Converter<Double, Double>(
    { this(it) },
    { reversed(it) }
)

