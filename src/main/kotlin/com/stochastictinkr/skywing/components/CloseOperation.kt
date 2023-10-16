package com.stochastictinkr.skywing.components

import javax.swing.WindowConstants

enum class CloseOperation(value:Int) {
    DO_NOTHING(WindowConstants.DO_NOTHING_ON_CLOSE),
    HIDE(WindowConstants.HIDE_ON_CLOSE),
    DISPOSE(WindowConstants.DISPOSE_ON_CLOSE),
    EXIT(WindowConstants.EXIT_ON_CLOSE),
    ;
    init {
        assert(value == ordinal)
    }
}