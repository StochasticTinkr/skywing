package com.stochastictinkr.skywing.uibuilder.dsl

@UiBuilderDsl
interface FrameConfig : WindowConfig {
    fun title(title: String)
    fun notResizable()
    fun resizable()
    fun undecorated()
    fun decorated()
}

