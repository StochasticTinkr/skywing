package com.stochastictinkr.skywing.utils

data class Converter<I, O>(val convert: (I) -> O, val revert: (O) -> I)

fun <I> toBooleanConverter(trueValue: I, falseValue: I, unmatchedIs: Boolean) =
    Converter<I, Boolean>(
        { it.toBoolean(trueValue, falseValue, unmatchedIs) },
        { it.toValue(trueValue, falseValue) }
    )

fun <O> fromBooleanConverter(trueValue: O, falseValue: O, unmatchedIs: Boolean) =
    Converter<Boolean, O>(
        { it.toValue(trueValue, falseValue) },
        { it.toBoolean(trueValue, falseValue, unmatchedIs) }
    )


private fun <T> Boolean.toValue(trueValue: T, falseValue: T) =
    if (this) trueValue else falseValue

private fun <T> T.toBoolean(trueValue: T, falseValue: T, unmatchedIs: Boolean) =
    when (this) {
        trueValue -> true
        falseValue -> false
        else -> unmatchedIs
    }

val DoubleToInt = Converter(Int::toDouble, Double::toInt)