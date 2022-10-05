package com.stochastictinkr.skywing.hints

import java.awt.RenderingHints

inline fun makeHints(init: Hints.() -> Unit): RenderingHints = RenderingHints(null).also { Hints(it).init() }