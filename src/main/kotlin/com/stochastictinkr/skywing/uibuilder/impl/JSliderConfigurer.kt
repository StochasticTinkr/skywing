package com.stochastictinkr.skywing.uibuilder.impl

import com.stochastictinkr.skywing.uibuilder.BoundedRangeModelConfig
import com.stochastictinkr.skywing.uibuilder.JComponentConfig
import com.stochastictinkr.skywing.uibuilder.JSliderConfig
import javax.swing.BoundedRangeModel
import javax.swing.JSlider

internal fun jSliderConfigurer(): Configurer<JSliderConfig, JSlider> =
    JSliderConfigurer().asConfigurer { slider ->
        jComponentConfigurer.configure(slider)
        slider.model = boundedRangeModelBuilder.build()
        slider.orientation = orientation
        slider.paintTicks = paintTicks
        slider.paintTrack = paintTrack
        slider.paintLabels = paintLabels
        slider.inverted = inverted
        slider.snapToTicks = snapToTicks
        slider.majorTickSpacing = majorTickSpacing
        slider.minorTickSpacing = minorTickSpacing
        val onChange = onChange
        val onAdjusting = onAdjusting
        if (onChange != null || onAdjusting != null)
            slider.model.addChangeListener {
                val source = it.source as BoundedRangeModel
                (if (source.valueIsAdjusting) onAdjusting else onChange)?.invoke(source.value)
            }
    }

internal fun jSliderBuilder() = jSliderConfigurer().asBuilder { JSlider() }

private class JSliderConfigurer(
    val jComponentConfigurer: Configurer<out JComponentConfig, in JSlider> = jComponentConfigurer(),
    val boundedRangeModelBuilder: Builder<out BoundedRangeModelConfig, out BoundedRangeModel> = boundedRangeModelBuilder(),
) : JSliderConfig,
    JComponentConfig by jComponentConfigurer.config, BoundedRangeModelConfig by boundedRangeModelBuilder.config {
    var onChange: ((Int) -> Unit)? = null
    var onAdjusting: ((Int) -> Unit)? = null
    var paintTicks: Boolean = false
    var paintTrack: Boolean = false
    var paintLabels: Boolean = false
    var snapToTicks: Boolean = false
    var inverted: Boolean = false
    var orientation = JSlider.HORIZONTAL
    var majorTickSpacing = 0
    var minorTickSpacing = 0

    override fun paintTicks() {
        paintTicks = true
    }

    override fun paintTrack() {
        paintTrack = true
    }

    override fun paintLabels() {
        paintLabels = true
    }

    override fun invert() {
        inverted = true
    }

    override fun horizontal() {
        orientation = JSlider.HORIZONTAL
    }

    override fun vertical() {
        orientation = JSlider.VERTICAL
    }

    override fun majorTickSpacing(spacing: Int) {
        majorTickSpacing = spacing
    }

    override fun minorTickSpacing(spacing: Int) {
        minorTickSpacing = spacing
    }

    override fun snapToTicks() {
        snapToTicks = true
    }

    override fun onChange(listener: (Int) -> Unit) {
        onChange = listener
    }

    override fun onAdjusting(listener: (Int) -> Unit) {
        onAdjusting = listener
    }

}