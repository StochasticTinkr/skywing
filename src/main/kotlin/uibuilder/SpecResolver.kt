package com.stoachstictinkr.skywing.uibuilder

interface SpecResolver {
    fun <C> resolve(ref: SpecRef<C>): C
}
