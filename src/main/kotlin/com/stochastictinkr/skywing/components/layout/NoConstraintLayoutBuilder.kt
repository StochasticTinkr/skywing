package com.stochastictinkr.skywing.components.layout

import com.stochastictinkr.skywing.utils.Init
import java.awt.Component
import java.awt.Container
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@JvmInline
value class NoConstraintLayoutBuilder(val container: Container) {

    inline fun <C : Component> add(component: C, init: Init<C> = {}): C {
        contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
        return component.apply(init).also(container::add)
    }

    inline fun <C : Component> add(count: Int, init: (Int) -> C) {
        contract { callsInPlace(init, InvocationKind.UNKNOWN) }
        repeat(count) { add(init(it)) }
    }

    inline fun <C : Component> add(source: Sequence<C>, init: Init<C> = {}) {
        contract { callsInPlace(init, InvocationKind.UNKNOWN) }
        source.forEach { add(it, init) }
    }
    inline fun <C : Component> add(source: Iterable<C>, init: Init<C> = {}) {
        contract { callsInPlace(init, InvocationKind.UNKNOWN) }
        source.forEach { add(it, init) }
    }
    inline fun <C : Component> add(vararg items: C, init: Init<C> = {}) {
        contract { callsInPlace(init, InvocationKind.UNKNOWN) }
        items.forEach { add(it, init) }
    }

    operator fun Component.unaryPlus(): Component = container.add(this)
}