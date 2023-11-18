package com.stochastictinkr.skywing.components.layout

import com.stochastictinkr.skywing.utils.Init
import java.awt.BorderLayout
import java.awt.Component
import java.awt.Container
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.reflect.KProperty


/**
 * The component that will be constrained to the north. See [BorderLayout.NORTH]
 */
var BorderLayoutBuilder.north: Component? by At(BorderLayout.NORTH)

/**
 * The component that will be constrained to the south. See [BorderLayout.SOUTH]
 */
var BorderLayoutBuilder.south: Component? by At(BorderLayout.SOUTH)

/**
 * The component that will be constrained to the east. See [BorderLayout.EAST]
 */
var BorderLayoutBuilder.east: Component? by At(BorderLayout.EAST)

/**
 * The component that will be constrained to the west. See [BorderLayout.WEST]
 */
var BorderLayoutBuilder.west: Component? by At(BorderLayout.WEST)

/**
 * The component that will be constrained to the center. See [BorderLayout.CENTER]
 */
var BorderLayoutBuilder.center: Component? by At(BorderLayout.CENTER)

/**
 * The component that will be constrained to the pageStart. See [BorderLayout.PAGE_START]
 */
var BorderLayoutBuilder.pageStart: Component? by At(BorderLayout.PAGE_START)

/**
 * The component that will be constrained to the pageEnd. See [BorderLayout.PAGE_END]
 */
var BorderLayoutBuilder.pageEnd: Component? by At(BorderLayout.PAGE_END)

/**
 * The component that will be constrained to the lineStart. See [BorderLayout.LINE_START]
 */
var BorderLayoutBuilder.lineStart: Component? by At(BorderLayout.LINE_START)

/**
 * The component that will be constrained to the lineEnd. See [BorderLayout.LINE_END]
 */
var BorderLayoutBuilder.lineEnd: Component? by At(BorderLayout.LINE_END)

/**
 * Provides a wrapper around [Container] with convenience methods for adding components with the correct constraints.
 * See [Container.borderLayout]
 */
@JvmInline
value class BorderLayoutBuilder(val parent: Container) {
    val borderLayout: BorderLayout get() = requireNotNull(parent.layout as? BorderLayout) { "Layout isn't BorderLayout" }

    /**
     * Set the component that will be constrained to the north. See [BorderLayout.NORTH]
     */
    inline fun <C : Component> north(component: C, init: Init<C> = {}): C {
        contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
        north = component.apply(init)
        return component
    }

    /**
     * Set the component that will be constrained to the south. See [BorderLayout.SOUTH]
     */
    inline fun <C : Component> south(component: C, init: Init<C> = {}): C {
        contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
        south = component.apply(init)
        return component
    }

    /**
     * Set the component that will be constrained to the east. See [BorderLayout.EAST]
     */
    inline fun <C : Component> east(component: C, init: Init<C> = {}): C {
        contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
        east = component.apply(init)
        return component
    }

    /**
     * Set the component that will be constrained to the west. See [BorderLayout.WEST]
     */
    inline fun <C : Component> west(component: C, init: Init<C> = {}): C {
        contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
        west = component.apply(init)
        return component
    }

    /**
     * Set the component that will be constrained to the center. See [BorderLayout.CENTER]
     */
    inline fun <C : Component> center(component: C, init: Init<C> = {}): C {
        contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
        center = component.apply(init)
        return component
    }

    /**
     * Set the component that will be constrained to the pageStart. See [BorderLayout.PAGE_START]
     */
    inline fun <C : Component> pageStart(component: C, init: Init<C> = {}): C {
        contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
        pageStart = component.apply(init)
        return component
    }

    /**
     * Set the component that will be constrained to the pageEnd. See [BorderLayout.PAGE_END]
     */
    inline fun <C : Component> pageEnd(component: C, init: Init<C> = {}): C {
        contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
        pageEnd = component.apply(init)
        return component
    }

    /**
     * Set the component that will be constrained to the lineStart. See [BorderLayout.LINE_START]
     */
    inline fun <C : Component> lineStart(component: C, init: Init<C> = {}): C {
        contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
        lineStart = component.apply(init)
        return component
    }

    /**
     * Set the component that will be constrained to the lineEnd. See [BorderLayout.LINE_END]
     */
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

/**
 * Provides a convenient way to build a component with a border layout.
 */
inline fun Container.borderLayout(
    hgap: Int = 0,
    vgap: Int = 0,
    addComponents: Init<BorderLayoutBuilder> = {},
): BorderLayoutBuilder {
    contract { callsInPlace(addComponents, InvocationKind.EXACTLY_ONCE) }
    if (layout !is BorderLayout) layout = BorderLayout(hgap, vgap)
    return BorderLayoutBuilder(parent).also(addComponents)
}
