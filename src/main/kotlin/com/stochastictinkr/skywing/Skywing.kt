package com.stochastictinkr.skywing

import java.awt.Dimension
import java.awt.EventQueue
import java.awt.Point
import java.awt.Rectangle
import java.awt.geom.Dimension2D
import java.awt.geom.Line2D
import java.awt.geom.Point2D
import java.awt.geom.Rectangle2D
import javax.swing.UIManager

fun initSkywing() {
    System.setProperty("apple.awt.application.appearance", "system")
    System.setProperty("apple.laf.useScreenMenuBar", "true")
    invokeLater {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
    }
}

fun invokeLater(run: Runnable) = EventQueue.invokeLater(run)
fun invokeAndWait(run: Runnable) = EventQueue.invokeAndWait(run)
val isEventDispatchThread get() = EventQueue.isDispatchThread()

infix fun Int.by(height: Int) = Dimension(this, height)

infix fun Point2D.to(other: Point2D) = Line2D.Double(this, other)
infix fun Dimension.at(point: Point) = Rectangle(point, this)
infix fun Dimension2D.at(point: Point2D): Rectangle2D = Rectangle2D.Double().also { it.setFrame(point, this) }