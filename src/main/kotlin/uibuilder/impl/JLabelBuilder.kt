package com.stochastictinkr.skywing.uibuilder.impl

import com.stochastictinkr.skywing.uibuilder.IconSpec
import com.stochastictinkr.skywing.uibuilder.JLabelSpec
import com.stochastictinkr.skywing.uibuilder.SpecRef
import com.stochastictinkr.skywing.uibuilder.SpecResolver
import com.stochastictinkr.skywing.uibuilder.buildIcon
import java.awt.Component
import javax.swing.Icon
import javax.swing.JLabel
import javax.swing.SwingConstants

class JLabelBuilder : AbstractJComponentBuilder<JLabel>(), JLabelSpec {
    private var iconTextGap: Int = 4
    private var verticalAlignment = SwingConstants.CENTER
    private var horizontalAlignment: Int? = null
    private var verticalTextPosition = SwingConstants.CENTER
    private var horizontalTextPosition = SwingConstants.TRAILING
    private var text: String? = null
    private var icon: Icon? = null
    private var disabledIcon: Icon? = null
    private var configureLabelFor: JLabel.(SpecResolver) -> Unit = {}
    private var configureMnemonic: JLabel.() -> Unit = {}
    override fun text(text: String) {
        this.text = text
    }

    override fun icon(icon: Icon) {
        this.icon = icon
    }

    override fun getInstance(resolver: SpecResolver) = JLabel()
    override fun icon(builder: IconSpec.() -> Icon) = icon(buildIcon(builder))
    override fun disabledIcon(icon: Icon) {
        disabledIcon = icon
    }

    override fun disabledIcon(builder: IconSpec.() -> Icon) = disabledIcon(buildIcon(builder))

    override fun labelFor(component: SpecRef<out Component>) {
        configureLabelFor = { resolver -> labelFor = resolver.resolve(component) }
    }

    override fun mnumonic(keyCode: Int) {
        configureMnemonic = { displayedMnemonic = keyCode }
    }

    override fun mnumonic(character: Char) {
        configureMnemonic = { setDisplayedMnemonic(character) }
    }

    override fun mnumonicIndex(index: Int) {
        configureMnemonic = { displayedMnemonicIndex = index }
    }

    override fun iconTextGap(px: Int) {
        iconTextGap = px
    }

    private class Builder(val set: (Int) -> Unit) : JLabelSpec.VerticalSpec, JLabelSpec.HorizontalSpec {
        override fun top() {
            set(SwingConstants.TOP)
        }

        override fun leading() {
            set(SwingConstants.LEADING)
        }

        override fun left() {
            set(SwingConstants.LEFT)
        }

        override fun center() {
            set(SwingConstants.CENTER)
        }

        override fun right() {
            set(SwingConstants.RIGHT)
        }

        override fun trailing() {
            set(SwingConstants.TRAILING)
        }

        override fun bottom() {
            set(SwingConstants.BOTTOM)
        }

    }

    override fun verticalTextPosition(spec: JLabelSpec.VerticalSpec.() -> Unit) {
        Builder { verticalTextPosition = it }.apply(spec)
    }

    override fun horizontalTextPosition(spec: JLabelSpec.HorizontalSpec.() -> Unit) {
        Builder { horizontalTextPosition = it }.apply(spec)
    }

    override fun verticalAlignment(spec: JLabelSpec.VerticalSpec.() -> Unit) {
        Builder { verticalAlignment = it }.apply(spec)
    }

    override fun horizontalAlignment(spec: JLabelSpec.HorizontalSpec.() -> Unit) {
        Builder { horizontalAlignment = it }.apply(spec)
    }

    override fun configure(component: JLabel, resolver: SpecResolver) {
        super.configure(component, resolver)
        component.iconTextGap = iconTextGap
        component.text = text
        component.icon = icon
        component.disabledIcon = disabledIcon
        component.horizontalAlignment =
            horizontalAlignment ?: if (icon != null) SwingConstants.CENTER else SwingConstants.LEADING
        component.verticalAlignment = verticalAlignment
        component.horizontalTextPosition = horizontalTextPosition
        component.verticalTextPosition = verticalTextPosition
        component.configureMnemonic()
        component.configureLabelFor(resolver)
    }

}
