package com.stochastictinkr.skywing.uibuilder.dsl

@UiBuilderDsl
interface DialogConfig : WindowConfig {
    fun title(title: String)
    fun nonModal()
    fun documentModal()
    fun applicationModal()
    fun toolkitModal()
    fun notResizable()
    fun resizable()
    fun undecorated()
    fun decorated()
}
