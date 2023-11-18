package com.stochastictinkr.skywing.components.models

import com.stochastictinkr.skywing.utils.DisposableAdapter1
import javax.swing.BoundedRangeModel
import javax.swing.DefaultBoundedRangeModel

fun boundedRangeModel(
    value: Int = 0,
    extent: Int = 0,
    minimum: Int = 0,
    maximum: Int = 100,
    adjusting: Boolean = false,
): BoundedRangeModel = DefaultBoundedRangeModel(value, extent, minimum, maximum).apply {
    valueIsAdjusting = adjusting
}

fun BoundedRangeModel.toRange() = minimum..maximum

private val changeListeners =
    DisposableAdapter1(BoundedRangeModel::addChangeListener, BoundedRangeModel::removeChangeListener)

fun BoundedRangeModel.onChange(listener: (Int) -> Unit) =
    changeListeners.add(this) { listener(value) }
