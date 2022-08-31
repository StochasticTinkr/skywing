package com.stochastictinkr.skywing.uibuilder

@UiBuilderDsl
interface FontConfig {
    fun dialog()
    fun dialogInput()
    fun sansSerif()
    fun serif()
    fun monospaced()
    fun bold()
    fun italic()
    fun pt(size: Int)
}
