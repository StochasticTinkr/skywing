package com.stockastictinkr.skying.examples

import com.stochastictinkr.skywing.components.createMenuBar
import com.stochastictinkr.skywing.components.events.withAction
import com.stochastictinkr.skywing.components.item
import com.stochastictinkr.skywing.components.layout.borderLayout
import com.stochastictinkr.skywing.components.mainFrame
import com.stochastictinkr.skywing.components.menu
import com.stochastictinkr.skywing.initSkywing
import java.awt.EventQueue
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JOptionPane

fun main() {
    initSkywing("Hello World")
    EventQueue.invokeLater {
        with(JFrame("Skywing Examples - Hello World")) {
            mainFrame()
            createMenuBar {
                menu(text = "Hello") {
                    item {
                        withAction("World") {
                            JOptionPane.showConfirmDialog(
                                this@with,
                                "Hello World",
                                "Skywing Example!",
                                JOptionPane.PLAIN_MESSAGE
                            )
                        }
                    }
                    item {
                        text = "Quit"
                        addActionListener {
                            println("Goodbye World")
                            dispose()
                        }
                    }
                }
            }

            borderLayout {
                center(JLabel("Hello World!")) {
                    font = font.deriveFont(64f)
                }
            }
            isVisible = true
        }
    }
}