package com.stochastictinkr.skywing.uibuilder

@UiBuilderDsl
interface FrameConfig : WindowConfig {
    fun title(title: String)
    fun notResizable()
    fun resizable()
    fun undecorated()
    fun decorated()
}

