package com.stochastictinkr.skywing.uibuilder

interface FontSpec {
    fun dialog()
    fun dialogInput()
    fun sansSerif()
    fun serif()
    fun monospaced()
    fun bold()
    fun italic()
    fun pt(size: Int)
}
