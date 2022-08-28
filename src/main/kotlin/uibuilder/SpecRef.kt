package com.stoachstictinkr.skywing.uibuilder

interface SpecRef<C> {
    fun getInstance(resolver: SpecResolver): C
    fun getConfiguredInstance(resolver: SpecResolver): C = getInstance(resolver)
}