package com.stochastictinkr.skywing.awt.geom

import java.awt.geom.Arc2D

inline fun arc(builder: Arc2D.()->Unit) = Arc2D.Double().apply(builder)