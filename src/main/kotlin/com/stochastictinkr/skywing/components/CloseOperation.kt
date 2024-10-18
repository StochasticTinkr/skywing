package com.stochastictinkr.skywing.components

import javax.swing.WindowConstants

/**
 * Possible operations to perform in response to a window close event. See [onClose]
 */
enum class CloseOperation(value: Int) {
    /**
     * See [WindowConstants.DO_NOTHING_ON_CLOSE]
     */
    DO_NOTHING(WindowConstants.DO_NOTHING_ON_CLOSE),

    /**
     * See [WindowConstants.HIDE_ON_CLOSE]
     */
    HIDE(WindowConstants.HIDE_ON_CLOSE),

    /**
     * See [WindowConstants.DISPOSE_ON_CLOSE]
     */
    DISPOSE(WindowConstants.DISPOSE_ON_CLOSE),

    /**
     * See [WindowConstants.EXIT_ON_CLOSE]
     */
    EXIT(WindowConstants.EXIT_ON_CLOSE),
    ;

    init {
        assert(value == ordinal)
    }
}