package com.stochastictinkr.skywing.utils

class DisposableAdapter0<T>(private val doAdd: (T) -> Unit, private val doRemove: (T) -> Unit) {
    fun add(value: T): Disposable {
        doAdd(value)
        return Disposable { doRemove(value) }
    }
}