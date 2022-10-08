package com.stochastictinkr.skywing.rendering

import java.awt.BasicStroke

data class StrokeBuilder(
    var width: Float = 1f,
    var endCap: StrokeCap = StrokeCap.SQUARE,
    var lineJoin: StrokeJoin = StrokeJoin.MITER,
    var miterLimit: Float = 10f,
    var dashPattern: MutableList<Float> = mutableListOf(),
    var dashPhase: Float = 0f,
) {
    fun roundedCap() {
        endCap = StrokeCap.ROUND
    }

    fun flatCap() {
        endCap = StrokeCap.BUTT
    }

    fun squareCap() {
        endCap = StrokeCap.SQUARE
    }

    fun roundedJoin() {
        lineJoin = StrokeJoin.ROUND
    }

    fun beveledJoin() {
        lineJoin = StrokeJoin.BEVEL
    }

    fun miteredJoin() {
        lineJoin = StrokeJoin.MITER
    }

    fun reset() {
        fromBasicStroke(BasicStroke())
    }

    fun addDash(vararg lengths: Float) {
        dashPattern.addAll(lengths.asList())
    }

    fun setDash(vararg lengths: Float) {
        clearDash()
        addDash(*lengths)
    }

    fun clearDash() {
        dashPattern.clear()
    }

    fun asBasicStroke() = BasicStroke(
        width,
        endCap.value,
        lineJoin.value,
        miterLimit,
        if (dashPattern.isEmpty()) null else dashPattern.toFloatArray(),
        dashPhase
    )

    fun fromBasicStroke(basicStroke: BasicStroke) {
        this.width = basicStroke.lineWidth
        this.endCap = StrokeCap.values().first { it.value == basicStroke.endCap }
        this.lineJoin = StrokeJoin.values().first { it.value == basicStroke.lineJoin }
        this.miterLimit = basicStroke.miterLimit
        this.dashPattern = basicStroke.dashArray?.toMutableList() ?: mutableListOf()
        this.dashPhase = basicStroke.dashPhase
    }
}