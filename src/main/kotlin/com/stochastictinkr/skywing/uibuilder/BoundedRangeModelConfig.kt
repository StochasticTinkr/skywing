package com.stochastictinkr.skywing.uibuilder

@UiBuilderDsl
interface BoundedRangeModelConfig {
    fun range(range: ClosedRange<Int>) {
        start(range.start); endInclusive(range.endInclusive)
    }

    fun start(start: Int)
    fun endInclusive(end: Int)
    fun extent(extent: Int)
    fun initialValue(value: Int)
}
