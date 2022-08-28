package com.stochastictinkr.skywing.uibuilder

interface SpecResolver {
    fun <C> resolve(ref: SpecRef<C>): C
}
