package com.stochastictinkr.skywing.components.layout

import com.stochastictinkr.skywing.utils.Init
import java.awt.BorderLayout
import java.awt.Component
import java.awt.Container
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.reflect.KProperty


var BorderLayoutBuilder.north: Component? by At(BorderLayout.NORTH)
var BorderLayoutBuilder.south: Component? by At(BorderLayout.SOUTH)
var BorderLayoutBuilder.east: Component? by At(BorderLayout.EAST)
var BorderLayoutBuilder.west: Component? by At(BorderLayout.WEST)
var BorderLayoutBuilder.center: Component? by At(BorderLayout.CENTER)
var BorderLayoutBuilder.pageStart: Component? by At(BorderLayout.PAGE_START)
var BorderLayoutBuilder.pageEnd: Component? by At(BorderLayout.PAGE_END)
var BorderLayoutBuilder.lineStart: Component? by At(BorderLayout.LINE_START)
var BorderLayoutBuilder.lineEnd: Component? by At(BorderLayout.LINE_END)

@JvmInline
value class BorderLayoutBuilder(val parent: Container) {
    val borderLayout: BorderLayout get() = requireNotNull(parent.layout as? BorderLayout) { "Layout isn't BorderLayout" }

    inline fun <C : Component> north(component: C, init: Init<C> = {}): C {
        contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
        north = component.apply(init)
        return component
    }

    inline fun <C : Component> south(component: C, init: Init<C> = {}): C {
        contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
        south = component.apply(init)
        return component
    }

    inline fun <C : Component> east(component: C, init: Init<C> = {}): C {
        contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
        east = component.apply(init)
        return component
    }

    inline fun <C : Component> west(component: C, init: Init<C> = {}): C {
        contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
        west = component.apply(init)
        return component
    }

    inline fun <C : Component> center(component: C, init: Init<C> = {}): C {
        contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
        center = component.apply(init)
        return component
    }

    inline fun <C : Component> pageStart(component: C, init: Init<C> = {}): C {
        contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
        pageStart = component.apply(init)
        return component
    }

    inline fun <C : Component> pageEnd(component: C, init: Init<C> = {}): C {
        contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
        pageEnd = component.apply(init)
        return component
    }

    inline fun <C : Component> lineStart(component: C, init: Init<C> = {}): C {
        contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
        lineStart = component.apply(init)
        return component
    }

    inline fun <C : Component> lineEnd(component: C, init: Init<C> = {}): C {
        contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
        lineEnd = component.apply(init)
        return component
    }
}

@JvmInline
private value class At(val place: String) {
    operator fun getValue(thisRef: BorderLayoutBuilder, property: KProperty<*>): Component? {
        return synchronized(thisRef.parent.treeLock) {
            thisRef.parent.components
        }.find { thisRef.borderLayout.getConstraints(it) == place }
    }

    operator fun setValue(thisRef: BorderLayoutBuilder, property: KProperty<*>, value: Component?) {
        if (value == null) {
            getValue(thisRef, property)?.let(thisRef.parent::remove)
        } else {
            thisRef.parent.add(value, place)
        }
    }
}

inline fun Container.borderLayout(
    hgap: Int = 0,
    vgap: Int = 0,
    addComponents: Init<BorderLayoutBuilder> = {},
): BorderLayoutBuilder {
    contract { callsInPlace(addComponents, InvocationKind.EXACTLY_ONCE) }
    if (layout !is BorderLayout) layout = BorderLayout(hgap, vgap)
    return BorderLayoutBuilder(parent).also(addComponents)
}
