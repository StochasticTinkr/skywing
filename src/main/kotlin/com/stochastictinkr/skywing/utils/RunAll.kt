package com.stochastictinkr.skywing.utils

fun <V, E : (V) -> Unit> List<E>.runAll(on: V) = forEach { it(on) }