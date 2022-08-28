package com.stochastictinkr.skywing

import com.stochastictinkr.skywing.uibuilder.SpecRef
import com.stochastictinkr.skywing.uibuilder.SpecResolver
import com.stochastictinkr.skywing.uibuilder.frame
import com.stochastictinkr.skywing.uibuilder.label
import java.util.IdentityHashMap

fun main() {
    initSkywing()

    val resolver = object : SpecResolver {
        val resolved = IdentityHashMap<SpecRef<*>, Any?>()

        @Suppress("UNCHECKED_CAST")
        override fun <C> resolve(ref: SpecRef<C>): C = resolved.computeIfAbsent(ref) { key ->
            key.getConfiguredInstance(this)
        } as C
    }
    invokeLater {
        val mainFrame = frame {
            centeredOnScreen()
            title("Skywing Test")
            label {
                border {
                    titled("XKCD")
                }
                icon { url("https://imgs.xkcd.com/comics/cloud_swirls.png") }
                text("Hello Clouds")
                horizontalTextPosition { center() }
                verticalTextPosition { bottom() }
                verticalAlignment { bottom() }
            }
            exitOnClose()
        }
        resolver.resolve(mainFrame).apply {
            pack()
            isVisible = true
        }
    }
}