package com.stochastictinkr.skywing.utils

class DisposableAdapter1<O, T>(private val doAdd: O.(T) -> Unit, private val doRemove: O.(T) -> Unit) {
    fun add(container: O, value: T): Disposable {
        container.doAdd(value)
        return Disposable { doRemove(container, value) }
    }
}

