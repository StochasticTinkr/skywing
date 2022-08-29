package com.stochastictinkr.skywing.uibuilder

interface SpecRef<C> {
    fun getInstance(resolver: SpecResolver): C
    fun getConfiguredInstance(resolver: SpecResolver): C = getInstance(resolver)
}

fun <C> SpecRef<C>?.resolveThen(resolver: SpecResolver, then: (C) -> Unit): Unit {
    if (this != null) resolver.resolve(this).also(then)
}

fun <C> SpecRef<C>?.resolveOrNull(resolver: SpecResolver, then: (C) -> Unit = {}): C? =
    if (this != null) resolver.resolve(this).apply(then) else null
