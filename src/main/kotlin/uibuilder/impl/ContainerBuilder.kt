package com.stochastictinkr.skywing.uibuilder.impl

import com.stochastictinkr.skywing.uibuilder.LayoutSpec
import com.stochastictinkr.skywing.uibuilder.SpecRef
import com.stochastictinkr.skywing.uibuilder.SpecResolver
import java.awt.Component
import java.awt.Container

class ContainerBuilder : LayoutSpec {
    private var children = mutableListOf<AddSpec>()
    override fun add(specRef: SpecRef<out Component>, constraints: Any?) {
        children.add(AddSpec(specRef, constraints))
    }

    override fun configureContainer(component: Container, resolver: SpecResolver) {
//        component.removeAll()
        children.forEach {
            component.add(resolver.resolve(it.ref), it.constraints, -1)
        }
    }

    private data class AddSpec(val ref: SpecRef<out Component>, val constraints: Any?)

}


