package com.stochastictinkr.skywing.components.layout

import com.stochastictinkr.skywing.utils.Init
import java.awt.Container
import java.awt.GridLayout
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract


fun Container.gridLayout(
    rows: Int = 1,
    columns: Int = 0,
    hgap: Int = 0,
    vgap: Int = 0,
    addComponents: Init<NoConstraintLayoutBuilder>,
): NoConstraintLayoutBuilder {
    contract { callsInPlace(addComponents, InvocationKind.EXACTLY_ONCE) }

    layout = GridLayout(rows, columns, hgap, vgap)
    return NoConstraintLayoutBuilder(this).also(addComponents)
}

