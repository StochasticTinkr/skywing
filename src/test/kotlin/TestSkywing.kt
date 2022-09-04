package com.stochastictinkr.skywing

import com.stochastictinkr.skywing.rendering.Antialiasing
import com.stochastictinkr.skywing.rendering.Rendering
import com.stochastictinkr.skywing.uibuilder.frame
import java.awt.BasicStroke
import java.awt.Color
import java.awt.event.ActionListener
import javax.swing.Timer
import kotlin.math.cos
import kotlin.math.sin

fun main() {
    initSkywing()

    invokeLater {
        frame {
            title("Skywing Test")
            packed()
            centeredOnScreen()
            scrollPane {
                box {
                    alignmentX(.5f)
                    slider {
                        border {
                            titled("Move")
                        }
                        range(1..100)
                        majorTickSpacing(10)
                        minorTickSpacing(2)
                        snapToTicks()
                        paintTicks()
                        paintTrack()
                        paintLabels()
                    }
                    var t = 0.0
                    custom {
                        alignmentX(.5f)
                        minimumSize(400 by 400)
                        preferredSize(400 by 400)
                        maximumSize(400 by 400)
                        background(Color.black)
                        foreground(Color(64, 16, 240))
                        painter {
                            g.translate(width/2, height/2)
                            g.rotate(t)
                            g.translate(-width/2, -height/2)
                            renderingHints(Rendering.QUALITY, Antialiasing.ON)
                            line(0, 0, 25 + cos(t) * 5, 25 + sin(t) * 5)
                            stroke = BasicStroke(3f)
                            rectangle(200.5, 100, 350, 350.5)
                        }
                    }.also { component ->
                        Timer(1000/60, ActionListener { t += 0.1; component.repaint() }).apply {
                            isRepeats = true
                        }.start()
                    }
                    label {
                        alignmentX(.5f)
                        border {
                            titled("Swirls")
                        }
                        icon { url("https://imgs.xkcd.com/comics/cloud_swirls.png") }
                        text("Hello Clouds")
                        textPosition { centerHorizontally(); bottom() }
                        alignment { centerHorizontally(); centerVertically() }
                        toolTip("'Why did you get into fluid dynamics?' 'Well, SOME planet has to have the coolest clouds, odds are it's not ours, and rockets are slow.'")
                    }
                }
            }
            exitOnClose()
        }.apply {
            isVisible = true
        }
    }
}