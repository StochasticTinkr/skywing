package com.stochastictinkr.skywing.hints

import java.awt.RenderingHints

sealed interface Hint {
    val key: RenderingHints.Key
    val value: Any?

    operator fun component1() = key
    operator fun component2() = value
}