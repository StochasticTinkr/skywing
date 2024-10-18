package com.stochastictinkr.skywing.geom

enum class ScalingFit {
    /**
     * Do not scale
     */
    NONE,

    /**
     * Preserve aspect and scale widths to equal
     */
    WIDTH,

    /**
     * Preserve aspect and scale heights to equal
     */
    HEIGHT,

    /**
     * Preserve aspect and scale the maximum amount such that either widths or heights equal
     */
    MAX,

    /**
     * Preserve aspect and scale the minimum amount such that either widths or heights are equal
     */
    MIN,

    /**
     * Do not preserve aspect.
     */
    STRETCH,
}
