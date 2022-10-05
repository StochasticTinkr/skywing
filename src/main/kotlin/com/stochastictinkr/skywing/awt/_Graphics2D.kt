package com.stochastictinkr.skywing.awt

import com.stochastictinkr.skywing.hints.Hints
import java.awt.Graphics2D

inline fun Graphics2D.hints(init: Hints.()->Unit) = Hints(this).init()