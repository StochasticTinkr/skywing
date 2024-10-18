package com.stochastictinkr.skywing.rendering

import com.stochastictinkr.skywing.geom.Radians
import com.stochastictinkr.skywing.geom.circle
import com.stochastictinkr.skywing.geom.component1
import com.stochastictinkr.skywing.geom.component2
import com.stochastictinkr.skywing.geom.div
import com.stochastictinkr.skywing.geom.plus
import com.stochastictinkr.skywing.geom.point
import com.stochastictinkr.skywing.geom.times
import java.awt.Color
import java.awt.GraphicsEnvironment
import java.lang.System.currentTimeMillis

fun main() {
    var angle = Radians(0.0)
    fullScreenRendering(
        graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().screenDevices.first(),
        updateState = {
            angle = Radians(Math.PI / 100000 * currentTimeMillis())
            Thread.sleep(13)
            true
        }) { (width, height) ->
        hints {
            renderingQuality()
            antialiasingOn()
        }
        background = Color.BLACK
        clearRect(0, 0, width, height)
        repeat(500) {
            color = Color.getHSBColor(it / 150f, 1f, 1f)
            fill(circle(point(width, height) / 2.0 + (angle * it.toDouble()).toPoint2D() * it, 5.0))
        }
    }
}