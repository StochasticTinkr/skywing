package com.stochastictinkr.skywing.geom

import com.stochastictinkr.skywing.utils.Init
import java.awt.Shape
import java.awt.geom.AffineTransform
import java.awt.geom.Area
import java.awt.geom.Line2D
import java.awt.geom.Path2D
import java.awt.geom.Point2D
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun affineTransform(init: Init<AffineTransform> = {}): AffineTransform {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    return AffineTransform().apply(init)
}

inline fun affineTransform(
    scaleX: Double = 1.0,
    shearY: Double = 0.0,
    shearX: Double = 0.0,
    scaleY: Double = 1.0,
    translateX: Double = 0.0,
    translateY: Double = 0.0,
    init: Init<AffineTransform> = {},
): AffineTransform {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    return AffineTransform(scaleX, shearY, shearX, scaleY, translateX, translateY).apply(init)
}

fun AffineTransform.copy(
    scaleX: Double = this.scaleX,
    shearY: Double = this.shearY,
    shearX: Double = this.shearX,
    scaleY: Double = this.scaleY,
    translateX: Double = this.translateX,
    translateY: Double = this.translateY,
) = affineTransform(
    scaleX = scaleX,
    shearY = shearY,
    shearX = shearX,
    scaleY = scaleY,
    translateX = translateX,
    translateY = translateY
)

fun AffineTransform.rotateAround(angle: com.stochastictinkr.skywing.geom.Angle, point: Point2D) = rotate(angle.radians, point.x, point.y)
fun AffineTransform.rotate(angle: com.stochastictinkr.skywing.geom.Angle) = rotate(angle.radians)

fun AffineTransform.component1() = scaleX
fun AffineTransform.component2() = shearY
fun AffineTransform.component3() = shearX
fun AffineTransform.component4() = scaleY
fun AffineTransform.component5() = translateX
fun AffineTransform.component6() = translateY

operator fun AffineTransform.plus(that: AffineTransform) = copy().apply { concatenate(that) }
operator fun AffineTransform.plusAssign(that: AffineTransform) = this.concatenate(that)

operator fun <T : Transformable<T>> AffineTransform.invoke(transformable: Transformable<T>) =
    transformable.transform(this)

operator fun AffineTransform.invoke(point: Point2D): Point2D = transform(point, null)
operator fun AffineTransform.invoke(line: Line2D): Line2D =
    doubleArrayOf(line.x1, line.y1, line.x2, line.y2)
        .let { pts ->
            transform(pts, 0, pts, 0, 2)
            line(pts[0], pts[1], pts[2], pts[3])
        }

operator fun AffineTransform.invoke(shape: Shape): Shape = Path2D.Double(shape, this)
operator fun AffineTransform.invoke(area: Area): Area = area.createTransformedArea(this)

@JvmName("transformEach")
fun <T : Transformable<T>> Iterable<Transformable<T>>.map(transform: AffineTransform) = map { transform(it) }

@JvmName("transformPoints")
fun Iterable<Point2D>.map(transform: AffineTransform) = map { transform(it) }

@JvmName("transformLines")
fun Iterable<Line2D>.map(transform: AffineTransform) = map { transform(it) }

@JvmName("transformAreas")
fun Iterable<Area>.map(transform: AffineTransform) = map { transform(it) }

@JvmName("transformShapes")
fun Iterable<Shape>.map(transform: AffineTransform) = map { transform(it) }

@JvmName("transformEach")
fun <T : Transformable<T>> Sequence<Transformable<T>>.map(transform: AffineTransform) = map { transform(it) }

@JvmName("transformPoints")
fun Sequence<Point2D>.map(transform: AffineTransform) = map { transform(it) }

@JvmName("transformLines")
fun Sequence<Line2D>.map(transform: AffineTransform) = map { transform(it) }

@JvmName("transformShapes")
fun Sequence<Shape>.map(transform: AffineTransform) = map { transform(it) }

@JvmName("transformAreas")
fun Sequence<Area>.map(transform: AffineTransform) = map { transform(it) }