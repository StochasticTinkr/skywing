package com.stochastictinkr.skywing.uibuilder.impl

import com.stochastictinkr.skywing.uibuilder.SpecRef
import com.stochastictinkr.skywing.uibuilder.SpecResolver

data class ObjectRef<C>(val obj: C) : SpecRef<C> {
    override fun getInstance(resolver: SpecResolver): C = obj
}