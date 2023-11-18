package com.stochastictinkr.skywing.utils

typealias Range = ClosedFloatingPointRange<Double>

fun IntRange.toDoubleRange() = start.toDouble()..endInclusive.toDouble()

val Range.delta get() = endInclusive - start

data class Scaling(
    val input: Range,
    val output: Range,
) : (Double) -> Double {
    val scale = output.delta / input.delta
    val reversed get() = Scaling(output, input)
    override operator fun invoke(value: Double) = (value - input.start) * scale + output.start
    fun reversed(value: Double) = (value - output.start) / scale + input.start
}

infix fun Range.scalingTo(other: Range) = Scaling(this, other)
infix fun IntRange.scalingTo(other: Range) = Scaling(this.toDoubleRange(), other)

fun Scaling.asConverter() = Converter<Double, Double>(
    { this(it) },
    { reversed(it) }
)

