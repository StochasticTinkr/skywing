package com.stochastictinkr.skywing.components

import com.stochastictinkr.skywing.components.models.onChange
import com.stochastictinkr.skywing.utils.DisposableAdapter1
import com.stochastictinkr.skywing.utils.ObservableProperty
import javax.swing.JSlider
import javax.swing.SwingConstants

enum class Orientation(value: Int) {
    HORIZONTAL(SwingConstants.HORIZONTAL),
    VERTICAL(SwingConstants.VERTICAL),
    ;

    init {
        assert(value == ordinal)
    }
}

private val changeListenerAdapter =
    DisposableAdapter1(JSlider::addChangeListener, JSlider::removeChangeListener)

fun JSlider.onChange(listener: (Int) -> Unit) =
    changeListenerAdapter.add(this) { listener(value) }

infix fun JSlider.bindTo(property: ObservableProperty<Int>) {
    value = property.get()
    model.onChange { property.set(it) }
//    property.addListener { value = it }
}
