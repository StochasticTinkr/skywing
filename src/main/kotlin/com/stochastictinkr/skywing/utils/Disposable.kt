package com.stochastictinkr.skywing.utils

fun interface Disposable {
    fun dispose()
}

data object NoOpDisposable : Disposable {
    override fun dispose() {}
}
