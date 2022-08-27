package com.stoachstictinkr.skywing

import java.awt.Color
import java.awt.Desktop
import javax.swing.JOptionPane

fun main() {
    initSkying()
    Desktop.getDesktop().setAboutHandler {
        JOptionPane.showMessageDialog(null, "This is Skywing", "About Skywing", JOptionPane.PLAIN_MESSAGE)
    }
    Desktop.getDesktop().setPreferencesHandler {
        JOptionPane.showMessageDialog(null, "This is Skywing", "About Skywing", JOptionPane.PLAIN_MESSAGE)
    }
    invokeLater {
        frame("Testing custom component") {
            exitOnClose()

            customComponent {
                preferredSize = 800 by 800
                painter = {
                    renderingHints(
                        Rendering.QUALITY
                    )
                    clear(Color.black)
                }
            }

            showPackedByPlatform()
        }
    }
}

