package com.stochastictinkr.skywing.rendering.hints

import java.awt.RenderingHints

sealed interface Hint {
    val key: RenderingHints.Key
    val value: Any?

    operator fun component1() = key
    operator fun component2() = value

    operator fun invoke(hints: Hints) = hints.set(this)
}
