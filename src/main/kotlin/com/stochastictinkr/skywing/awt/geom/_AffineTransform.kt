package com.stochastictinkr.skywing.awt.geom

import java.awt.Shape
import java.awt.geom.AffineTransform
import java.awt.geom.Area
import java.awt.geom.Path2D
import java.awt.geom.Point2D

inline fun makeTransform(init: AffineTransform.() -> Unit) = AffineTransform().apply(init)

operator fun AffineTransform.plusAssign(other: AffineTransform) = this.concatenate(other)

operator fun <T : Transformable<T>> AffineTransform.invoke(transformable: Transformable<T>) =
    transformable.transform(this)

operator fun AffineTransform.invoke(point: Point2D): Point2D = transform(point, Point2D.Double())
operator fun AffineTransform.invoke(shape: Shape): Shape = Path2D.Double(shape, this)
operator fun AffineTransform.invoke(area: Area): Shape = area.createTransformedArea(this)

@JvmName("transformEach")
fun <T : Transformable<T>> Iterable<T>.map(transform: AffineTransform) = map { transform(it) }

@JvmName("transformPoint")
fun Iterable<Point2D>.map(transform: AffineTransform) = map { transform(it) }


@JvmName("transformArea")
fun Iterable<Area>.map(transform: AffineTransform) = map { transform(it) }

@JvmName("transformShape")
fun Iterable<Shape>.map(transform: AffineTransform) = map { transform(it) }
fun <T> Iterable<T>.mapTransformable(transform: AffineTransform) = mapNotNull {
    when (it) {
        is Point2D -> transform(it)
        is Area -> transform(it)
        is Shape -> transform(it)
        is Transformable<*> -> transform(it)
        else -> null
    }
}

@JvmName("transformEach")
fun <T : Transformable<T>> Sequence<T>.map(transform: AffineTransform) = map { transform(it) }

@JvmName("transformPoint")
fun Sequence<Point2D>.map(transform: AffineTransform) = map { transform(it) }

@JvmName("transformShape")
fun Sequence<Shape>.map(transform: AffineTransform) = map { transform(it) }

@JvmName("transformArea")
fun Sequence<Area>.map(transform: AffineTransform) = map { transform(it) }

fun <T> Sequence<T>.mapTransformable(transform: AffineTransform) = mapNotNull {
    when (it) {
        is Point2D -> transform(it)
        is Shape -> transform(it)
        is Transformable<*> -> transform(it)
        else -> null
    }
}
