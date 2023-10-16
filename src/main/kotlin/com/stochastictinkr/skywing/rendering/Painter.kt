package com.stochastictinkr.skywing.rendering

import java.awt.Dimension
import java.awt.Graphics2D

typealias Painter = Graphics2D.(size: Dimension) -> Unit