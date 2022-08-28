package com.stoachstictinkr.skywing

import com.stoachstictinkr.skywing.uibuilder.SpecRef
import com.stoachstictinkr.skywing.uibuilder.SpecResolver
import com.stoachstictinkr.skywing.uibuilder.label
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
                icon { url("https://imgs.xkcd.com/comics/cloud_swirls.png") }
            }
            exitOnClose()
        }
        resolver.resolve(mainFrame).apply {
            pack()
            isVisible = true
        }
    }
}