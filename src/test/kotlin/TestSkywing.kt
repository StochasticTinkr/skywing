package com.stochastictinkr.skywing

import com.stochastictinkr.skywing.uibuilder.frame

fun main() {
    initSkywing()

    invokeLater {
        val mainFrame = frame {
            title("Skywing Test")
            packed()
            centeredOnScreen()
            label {
                border {
                    titled("Swirls")
                }
                icon { url("https://imgs.xkcd.com/comics/cloud_swirls.png") }
                text("Hello Clouds")
                textPosition { centerHorizontally(); bottom() }
                alignment { centerHorizontally(); centerVertically() }
                toolTip("https://xkcd.com/2664/")
            }
            exitOnClose()
        }.apply {
            isVisible = true
        }
    }
}