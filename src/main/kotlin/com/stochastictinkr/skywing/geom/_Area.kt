package com.stochastictinkr.skywing.geom

import com.stochastictinkr.skywing.utils.Init
import java.awt.Shape
import java.awt.geom.Area
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun area(init: Init<Area> = {}): Area {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    return Area().apply(init)
}

inline fun Shape.asArea(init: Init<Area> = {}): Area {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    return Area(this).apply(init)
}

inline fun Area.copy(init: Init<Area>) = Area(this).apply(init)

operator fun Area.plus(other: Area) = copy { add(other) }
operator fun Area.plusAssign(other: Area) = add(other)
operator fun Area.minus(other: Area) = copy { subtract(other) }
operator fun Area.minusAssign(other: Area) = subtract(other)

infix fun Area.intersection(other: Area) = copy { intersect(other) }
infix fun Area.xor(other: Area) = copy { exclusiveOr(other) }
