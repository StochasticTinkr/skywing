package com.stochastictinkr.skywing.awt.hints

import com.stochastictinkr.skywing.hints.Hints
import java.awt.RenderingHints

inline operator fun RenderingHints.invoke(init: Hints.() -> Unit) = apply { Hints(this).init() }
