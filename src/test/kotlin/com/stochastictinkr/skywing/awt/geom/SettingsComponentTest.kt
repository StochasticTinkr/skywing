package com.stochastictinkr.skywing.awt.geom

import com.stochastictinkr.skywing.components.addComponent
import com.stochastictinkr.skywing.initSkywing
import com.stochastictinkr.skywing.components.configureAndShow
import com.stochastictinkr.skywing.components.mainFrame
import com.stochastictinkr.skywing.components.settingsLayout
import com.stochastictinkr.skywing.utils.asObservable
import com.stochastictinkr.skywing.utils.observable
import java.awt.BorderLayout
import java.awt.EventQueue.invokeLater
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.Timer

fun main() {
    initSkywing("Testing settings component")
    invokeLater {
        val values = MyValues()

        JFrame().configureAndShow {
            mainFrame()
            addComponent(BorderLayout.EAST, component = JPanel()) {
                settingsLayout {
                    slider(100, 0, property = values::myInt.asObservable()) { "My Int: $it" }
                    slider(-5.0, 5.0, 1000, property = values::myDouble.asObservable()) { "Doubles: $it" }
                    checkbox(property = values::myBoolean.asObservable()) { "Check it" }
                }
            }
        }

        Timer(100) {
            println("${values.myBoolean}, ${values.myInt}, ${values.myDouble}")
        }.apply {
            isRepeats = true

            start()
        }
    }
}


class MyValues {
    var myInt by observable(0)
    var myBoolean by observable(false)
    var myDouble by observable(0.0)
}