package com.stoachstictinkr.skywing

import com.stoachstictinkr.skywing.uibuilder.SpecRef
import com.stoachstictinkr.skywing.uibuilder.SpecResolver
import java.awt.Window


class WindowBuilder : AbstractWindowBuilder<Window>() {
    private var owner: SpecRef<out Window>? = null

    override fun getInstance(resolver: SpecResolver): Window = Window(owner?.run(resolver::resolve))
}
