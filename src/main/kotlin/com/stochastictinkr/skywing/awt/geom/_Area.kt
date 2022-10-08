package com.stochastictinkr.skywing.awt.geom

import java.awt.geom.Area

inline fun area(init: Area.() -> Unit) = Area().apply(init)
inline fun Area.copy(init: Area.() -> Unit) = Area(this).apply(init)


operator fun Area.plus(other: Area) = copy { add(other) }
operator fun Area.plusAssign(other: Area) = add(other)
operator fun Area.minus(other: Area) = copy { subtract(other) }
operator fun Area.minusAssign(other: Area) = subtract(other)

infix fun Area.intersection(other: Area) = copy { intersect(other) }
infix fun Area.xor(other: Area) = copy { exclusiveOr(other) }
