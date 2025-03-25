package com.stochastictinkr.skywing.components

import com.stochastictinkr.skywing.components.borders.*
import com.stochastictinkr.skywing.components.layout.*
import com.stochastictinkr.skywing.components.models.*
import com.stochastictinkr.skywing.utils.*
import java.awt.*
import java.awt.GridBagConstraints.*
import java.util.*
import javax.swing.*
import javax.swing.border.*
import kotlin.math.*

/**
 * A builder for creating a settings layout.
 */
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
        slider(
            inverted = start > end,
            extent = extent,
            minimum = min(start, end),
            maximum = max(start, end),
            minorTickSpacing = minorTickSpacing,
            majorTickSpacing = majorTickSpacing,
            snapToTicks = snapToTicks,
            tickLabels = tickLabels,
            paintLabels = paintLabels,
            bindProperty = property,
            valueProperty = property,
            borderFactory = borderFactory,
            label = label
        )
    }

    /**
     * Add a slider to the layout.
     *
     * @param start The minimum value of the slider.
     * @param end The maximum value of the slider.
     * @param minorSteps The number of minor steps between the start and end values.
     * @param majorSteps The number of major steps between the start and end values.
     * @param minorTickSpacing The number of minor steps between each tick.
     * @param majorTickSpacing The number of major steps between each major tick.
     * @param property The property to bind the slider to. This will be bound to the slider's value.
     * @param borderFactory The factory to create the border for the slider. This will be called with the label text.
     * @param label The label to display next to the slider. If this returns non-null, it create or replace the border with the label.
     */
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
        slider(
            inverted = start > end,
            extent = 0,
            minimum = 0,
            maximum = minorSteps,
            minorTickSpacing = minorTickSpacing,
            majorTickSpacing = majorTickSpacing,
            snapToTicks = false,
            tickLabels = null,
            paintLabels = false,
            bindProperty = property.convertingTo(0..minorSteps scalingTo start..end).asInt(),
            valueProperty = property,
            borderFactory = borderFactory,
            label = label
        )
    }

    private fun <N> slider(
        inverted: Boolean,
        extent: Int,
        minimum: Int,
        maximum: Int,
        minorTickSpacing: Int,
        majorTickSpacing: Int,
        snapToTicks: Boolean = true,
        tickLabels: Map<Int, JComponent>? = null,
        paintLabels: Boolean = !tickLabels.isNullOrEmpty(),
        bindProperty: ObservableProperty<Int>,
        valueProperty: ObservableProperty<N>,
        borderFactory: (label: String?) -> Border?,
        label: (N) -> String?,
    ) {
        val slider = JSlider(
            boundedRangeModel(bindProperty.get(), extent, minimum, maximum)
        ).apply {
            this.orientation = orientation
            this.majorTickSpacing = majorTickSpacing
            this.minorTickSpacing = minorTickSpacing
            tickLabels?.let { this.labelTable = Hashtable(it) }
            this.inverted = inverted
            this.snapToTicks = snapToTicks
            this.paintTicks = paintLabels
        }
        container.addComponent(rowConstraints, component = slider) {
            copyColors()
        }
        fun updateBorder() {
            slider.border = borderFactory(label(valueProperty.get()))
        }
        slider bindTo bindProperty
        updateBorder()
        valueProperty.addListener { updateBorder() }
    }

    /**
     * Add a checkbox to the layout.
     *
     * @param checkbox The checkbox to add to the layout.
     * @param property The property to bind the checkbox to. This will be bound to the checkbox's selected state.
     * @param label The label to display next to the checkbox. If this returns non-null, it will update the checkbox text.
     */
    fun checkbox(
        checkbox: JCheckBox = jCheckBox().apply {
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

/**
 * Layout the container with a settings layout.
 */
fun Container.settingsLayout(addComponents: Init<SettingsLayoutBuilder> = {}): SettingsLayoutBuilder {
    layout = GridBagLayout()
    return SettingsLayoutBuilder(this).also(addComponents)
}