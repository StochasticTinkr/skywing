package com.stochastictinkr.skywing.uibuilder

import javax.swing.JSlider
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
    fun onChange(listener: JSlider.(Int)->Unit)
    fun onAdjusting(listener:  JSlider.(Int)->Unit)
}