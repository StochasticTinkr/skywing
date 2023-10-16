package com.stochastictinkr.skywing.components

import com.stochastictinkr.skywing.components.borders.EtchedType
import com.stochastictinkr.skywing.components.borders.TitleJustification
import com.stochastictinkr.skywing.components.borders.etched
import com.stochastictinkr.skywing.components.borders.titled
import com.stochastictinkr.skywing.components.layout.gridBagConstraints
import com.stochastictinkr.skywing.components.models.boundedRangeModel
import com.stochastictinkr.skywing.utils.Init
import com.stochastictinkr.skywing.utils.ObservableProperty
import com.stochastictinkr.skywing.utils.asInt
import com.stochastictinkr.skywing.utils.convertingTo
import com.stochastictinkr.skywing.utils.scalingTo
import java.awt.Color
import java.awt.Component
import java.awt.Container
import java.awt.GridBagConstraints.HORIZONTAL
import java.awt.GridBagConstraints.LINE_START
import java.awt.GridBagConstraints.REMAINDER
import java.awt.GridBagLayout
import java.util.Hashtable
import javax.swing.JCheckBox
import javax.swing.JComponent
import javax.swing.JSlider
import javax.swing.SwingConstants
import javax.swing.border.Border
import kotlin.math.max
import kotlin.math.min

data class SettingsLayoutBuilder(val container: Container) {
    var defaultTitleColor: Color? = null
    var defaultBorderShadowColor: Color? = null
    var defaultBorderFactory: (label: String?) -> Border? = { label ->
        titled(
            title = label,
            border = etched(EtchedType.RAISED, defaultTitleColor, defaultBorderShadowColor),
            color = defaultTitleColor,
            justification = TitleJustification.LEADING
        )
    }


    private val rowConstraints = gridBagConstraints(
        anchor = LINE_START,
        gridwidth = REMAINDER,
        fill = HORIZONTAL,
        weightx = 1.0
    )

    fun slider(
        start: Int,
        end: Int,
        extent: Int = 0,
        minorTickSpacing: Int = 0,
        majorTickSpacing: Int = 0,
        snapToTicks: Boolean = true,
        tickLabels: Map<Int, JComponent>? = null,
        paintLabels: Boolean = !tickLabels.isNullOrEmpty(),
        property: ObservableProperty<Int>,
        borderFactory: (label: String?) -> Border? = defaultBorderFactory,
        label: (Int) -> String? = { null },
    ) {
        val slider = JSlider(
            boundedRangeModel(property.get(), extent, min(start, end), max(start, end))
        ).apply {
            this.orientation = orientation
            this.majorTickSpacing = majorTickSpacing
            this.minorTickSpacing = minorTickSpacing
            tickLabels?.let { this.labelTable = Hashtable(it) }
            this.inverted = start > end
            this.snapToTicks = snapToTicks
            this.paintTicks = paintLabels || minorTickSpacing > 1
            this.paintLabels = paintLabels
        }
        container.addComponent(rowConstraints, component = slider) {
            copyColors()
        }
        fun updateBorder() {
            slider.border = borderFactory(label(property.get()))
        }
        slider bindTo property
        updateBorder()
        property.addListener { updateBorder() }
    }

    fun slider(
        start: Double = -1.0,
        end: Double = 1.0,
        minorSteps: Int = 100,
        majorSteps: Int = 10,
        minorTickSpacing: Int = 1,
        majorTickSpacing: Int = minorSteps / majorSteps,
        property: ObservableProperty<Double>,
        borderFactory: (label: String?) -> Border? = defaultBorderFactory,
        label: (Double) -> String? = { null },
    ) {
        val converted = property.convertingTo(0..minorSteps scalingTo start..end).asInt()
        val slider = JSlider(
            boundedRangeModel(converted.get(), 0, 0, minorSteps)
        ).apply {
            this.orientation = orientation
            this.majorTickSpacing = majorTickSpacing
            this.minorTickSpacing = minorTickSpacing
            this.inverted = start > end
            this.snapToTicks = true
            this.paintTicks = true
        }
        container.addComponent(rowConstraints, component = slider) {
            copyColors()
        }
        fun updateBorder() {
            slider.border = borderFactory(label(property.get()))
        }
        slider bindTo converted
        updateBorder()
        property.addListener { updateBorder() }
    }

    fun checkbox(
        checkbox: JCheckBox = checkbox().apply {
            horizontalTextPosition = SwingConstants.LEADING
        },
        property: ObservableProperty<Boolean>,
        label: (Boolean) -> String? = { null },
    ) {
        container.addComponent(rowConstraints, component = checkbox) {
            copyColors()
        }
        checkbox bindTo property
        fun updateLabel() {
            checkbox.text = label(property.get())
        }
        updateLabel()
        property.addListener { updateLabel() }
    }

    private fun Component.copyColors() {
        foreground = container.foreground
        background = container.background
    }

}

fun Container.settingsLayout(addComponents: Init<SettingsLayoutBuilder> = {}): SettingsLayoutBuilder {
    layout = GridBagLayout()
    return SettingsLayoutBuilder(this).also(addComponents)
}