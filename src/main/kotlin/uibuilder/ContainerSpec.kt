package com.stochastictinkr.skywing.uibuilder

import java.awt.Component
import java.awt.Container

interface ContainerSpec {
    fun add(specRef: SpecRef<out Component>, constraints: Any? = null)
    fun configureContainer(component: Container, resolver: SpecResolver)
}

object NotContained : ContainerSpec {
    override fun add(specRef: SpecRef<out Component>, constraints: Any?){}
    override fun configureContainer(component: Container, resolver: SpecResolver) = Unit
}
