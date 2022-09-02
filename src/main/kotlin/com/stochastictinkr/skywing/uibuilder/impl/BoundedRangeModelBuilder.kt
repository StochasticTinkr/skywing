package com.stochastictinkr.skywing.uibuilder.impl

import com.stochastictinkr.skywing.uibuilder.BoundedRangeModelConfig
import javax.swing.BoundedRangeModel
import javax.swing.DefaultBoundedRangeModel

internal fun boundedRangeModelBuilder(): Builder<BoundedRangeModelConfig, BoundedRangeModel> =
    BoundedRangeModelBuilder().asBuilder {
        DefaultBoundedRangeModel().apply {  setRangeProperties(value, extent, start, endInclusive, false) }
    }

private class BoundedRangeModelBuilder : BoundedRangeModelConfig {
    var start: Int = 0
    var endInclusive = 0
    var extent = 0
    var value = 0
    override fun start(start: Int) {
        this.start = start
    }

    override fun endInclusive(end: Int) {
        this.endInclusive = end
    }

    override fun extent(extent: Int) {
        this.extent = extent
    }

    override fun initialValue(value: Int) {
        this.value = value
    }
}