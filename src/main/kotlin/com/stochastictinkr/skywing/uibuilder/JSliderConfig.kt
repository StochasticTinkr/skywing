package com.stochastictinkr.skywing.uibuilder

import javax.swing.event.ChangeEvent


interface JSliderConfig : JComponentConfig, BoundedRangeModelConfig {
    fun paintTicks()
    fun paintTrack()
    fun paintLabels()
    fun invert()
    fun horizontal()
    fun vertical()
    fun majorTickSpacing(spacing: Int)
    fun minorTickSpacing(spacing: Int)
    fun snapToTicks()
    fun onChange(listener: (Int)->Unit)
    fun onAdjusting(listener: (Int)->Unit)
}