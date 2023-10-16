package com.stochastictinkr.skywing.components.layout

import com.stochastictinkr.skywing.utils.Init
import java.awt.Container
import javax.swing.BoxLayout
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

enum class BoxAxis(value: Int) {
    X(BoxLayout.X_AXIS),
    Y(BoxLayout.Y_AXIS),
    LINE(BoxLayout.LINE_AXIS),
    PAGE(BoxLayout.PAGE_AXIS),
    ;

    init {
        assert(ordinal == value)
    }
}

inline fun Container.boxLayout(
    axis: BoxAxis,
    addComponents: Init<NoConstraintLayoutBuilder> = {},
): NoConstraintLayoutBuilder {
    contract { callsInPlace(addComponents, InvocationKind.EXACTLY_ONCE) }

    layout = BoxLayout(this, axis.ordinal)
    return NoConstraintLayoutBuilder(this).also(addComponents)
}

inline fun Container.horizontalBoxLayout(addComponents: Init<NoConstraintLayoutBuilder> = {}): NoConstraintLayoutBuilder {
    contract { callsInPlace(addComponents, InvocationKind.EXACTLY_ONCE) }
    return boxLayout(BoxAxis.X, addComponents)
}

inline fun Container.verticalBoxLayout(addComponents: Init<NoConstraintLayoutBuilder> = {}): NoConstraintLayoutBuilder {
    contract { callsInPlace(addComponents, InvocationKind.EXACTLY_ONCE) }
    return boxLayout(BoxAxis.Y, addComponents)
}

inline fun Container.lineBoxLayout(addComponents: Init<NoConstraintLayoutBuilder> = {}): NoConstraintLayoutBuilder {
    contract { callsInPlace(addComponents, InvocationKind.EXACTLY_ONCE) }
    return boxLayout(BoxAxis.LINE, addComponents)
}

inline fun Container.pageBoxLayout(addComponents: Init<NoConstraintLayoutBuilder> = {}): NoConstraintLayoutBuilder {
    contract { callsInPlace(addComponents, InvocationKind.EXACTLY_ONCE) }
    return boxLayout(BoxAxis.PAGE, addComponents)
}
