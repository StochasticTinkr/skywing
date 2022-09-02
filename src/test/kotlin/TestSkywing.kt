package com.stochastictinkr.skywing

import com.stochastictinkr.skywing.uibuilder.frame
import java.awt.Color

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
                    custom {
                        alignmentX(.5f)
                        minimumSize(400 by 400)
                        preferredSize(400 by 400)
                        maximumSize(400 by 400)
                        background(Color.black)
                        foreground(Color(64, 16, 240))
                        painter {
                            clear()
                            line(0, 0, 25, 25)
                            rectangle(200, 100, 350, 350)
                        }
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
                        toolTip("https://xkcd.com/2664/")
                    }
                }
            }
            exitOnClose()
        }.apply {
            isVisible = true
        }
    }
}