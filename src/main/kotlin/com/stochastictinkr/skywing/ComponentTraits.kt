package com.stochastictinkr.skywing

import java.awt.Component
import java.util.WeakHashMap

class ComponentTraits<T : Any> {
    private val traits = WeakHashMap<Component, T>()
    operator fun get(component: Component): T? = traits[component]
    fun computeIfAbsent(component: Component, mapping: (Component) -> T): T = traits.computeIfAbsent(component, mapping)
    fun <R> whenPresent(component: Component, action: (T) -> R, default: R? = null): R? =
        traits[component]?.run(action) ?: default

    operator fun set(component: Component, value: T) {
        traits[component] = value
    }

    fun contains(component: Component) = component in traits.keys
}

