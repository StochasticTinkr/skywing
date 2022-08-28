package com.stochastictinkr.skywing.uibuilder.impl

import com.stochastictinkr.skywing.uibuilder.BorderSpec
import com.stochastictinkr.skywing.uibuilder.JComponentSpec
import com.stochastictinkr.skywing.uibuilder.SpecRef
import com.stochastictinkr.skywing.uibuilder.SpecResolver
import com.stochastictinkr.skywing.uibuilder.ref
import com.stochastictinkr.skywing.uibuilder.resolveThen
import javax.swing.JComponent
import javax.swing.border.Border

abstract class AbstractJComponentBuilder<C : JComponent> : AbstractComponentBuilder<C>(), JComponentSpec<C> {
    private var border: SpecRef<Border?>? = null
    override fun border(border: Border) {
        this.border = ref(border)
    }

    override fun border(spec: BorderSpec.() -> Unit) {
        this.border = BorderBuilder().apply(spec)
    }

    override fun configure(component: C, resolver: SpecResolver) {
        super.configure(component, resolver)
        border.resolveThen(resolver, component::setBorder)
    }
}
