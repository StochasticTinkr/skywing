
package com.stochastictinkr.skywing.awt.geom

import java.awt.Shape
import java.awt.geom.AffineTransform
import java.awt.geom.Area
import java.awt.geom.Line2D
import java.awt.geom.Path2D
import java.awt.geom.Point2D
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
inline fun makeTransform(init: AffineTransform.() -> Unit): AffineTransform {
    contract {
        callsInPlace(init, InvocationKind.EXACTLY_ONCE)
    }

    return AffineTransform().apply(init)
}

operator fun AffineTransform.plusAssign(other: AffineTransform) = this.concatenate(other)

operator fun <T : Transformable<T>> AffineTransform.invoke(transformable: Transformable<T>) =
    transformable.transform(this)

operator fun AffineTransform.invoke(point: Point2D): Point2D = transform(point, Point2D.Double())
operator fun AffineTransform.invoke(line: Line2D): Line2D =
    doubleArrayOf(line.x1, line.y1, line.x2, line.y2)
        .let { pts ->
            transform(pts, 0, pts, 0, 2)
            line(pts[0], pts[1], pts[2], pts[3])
        }

operator fun AffineTransform.invoke(shape: Shape): Shape = Path2D.Double(shape, this)
operator fun AffineTransform.invoke(area: Area): Area = area.createTransformedArea(this)

@JvmName("transformEach")
fun <T : Transformable<T>> Iterable<T>.map(transform: AffineTransform) = map { transform(it) }

@JvmName("transformPoints")
fun Iterable<Point2D>.map(transform: AffineTransform) = map { transform(it) }


@JvmName("transformAreas")
fun Iterable<Area>.map(transform: AffineTransform) = map { transform(it) }

@JvmName("transformShapes")
fun Iterable<Shape>.map(transform: AffineTransform) = map { transform(it) }

@JvmName("transformEach")
fun <T : Transformable<T>> Sequence<T>.map(transform: AffineTransform) = map { transform(it) }

@JvmName("transformPoints")
fun Sequence<Point2D>.map(transform: AffineTransform) = map { transform(it) }

@JvmName("transformShapes")
fun Sequence<Shape>.map(transform: AffineTransform) = map { transform(it) }

@JvmName("transformAreas")
fun Sequence<Area>.map(transform: AffineTransform) = map { transform(it) }
