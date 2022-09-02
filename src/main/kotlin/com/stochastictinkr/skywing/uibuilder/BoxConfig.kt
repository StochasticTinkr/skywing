package com.stochastictinkr.skywing.uibuilder

interface BoxConfig : JComponentConfig, ManagedLayoutConfig {
    fun page()
    fun line()
    fun vertical()
    fun horizontal()
}