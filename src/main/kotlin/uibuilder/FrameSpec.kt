package com.stoachstictinkr.skywing.uibuilder

import java.awt.Frame

interface FrameSpec<C:Frame> : WindowSpec<C>  {
    fun title(title: String)
    fun notResizable()
    fun resizable()
    fun undecorated()
    fun decorated()
}

