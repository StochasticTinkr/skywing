package com.stochastictinkr.skywing.uibuilder.impl

import com.stochastictinkr.skywing.uibuilder.IconConfig
import com.stochastictinkr.skywing.uibuilder.JComponentConfig
import com.stochastictinkr.skywing.uibuilder.JLabelConfig
import java.awt.Component
import javax.swing.Icon
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.SwingConstants

internal fun buildJLabel(init: JLabelConfig.() -> Unit): JLabel =
    JLabelConfigurer().run {
        init()
        val label = JLabel()
        jComponentConfigurer.configure(label)
        label.text = text
        label.icon = icon
        label.disabledIcon = disabledIcon
        label.labelFor = labelFor
        label.mnemonicSetter()
        mnemonicIndex?.let { label.displayedMnemonicIndex = it }
        label.iconTextGap = iconTextGap
        label.verticalTextPosition = verticalTextPosition
        label.horizontalTextPosition = horizontalTextPosition
        label.verticalAlignment = verticalAlignment
        label.horizontalAlignment =
            horizontalAlignment ?: if (icon != null) SwingConstants.CENTER else SwingConstants.LEADING
        label
    }

private class JLabelConfigurer(val jComponentConfigurer: Configurer<JComponentConfig, JComponent> = jComponentConfigurer()) :
    JLabelConfig, JComponentConfig by jComponentConfigurer.config {
    var text: String? = null
    var icon: Icon? = null
    var disabledIcon: Icon? = null
    var labelFor: Component? = null
    var mnemonicSetter: JLabel.() -> Unit = { }
    var mnemonicIndex: Int? = null
    var iconTextGap = 4
    var verticalAlignment: Int = SwingConstants.CENTER
    var horizontalAlignment: Int? = null
    var verticalTextPosition: Int = SwingConstants.CENTER
    var horizontalTextPosition: Int = SwingConstants.TRAILING

    override fun text(text: String) {
        this.text = text
    }

    override fun icon(icon: Icon) {
        this.icon = icon
    }

    override fun icon(init: IconConfig.() -> Unit) {
        this.icon = buildIcon(init)
    }

    override fun disabledIcon(icon: Icon) {
        this.disabledIcon = icon
    }

    override fun disabledIcon(init: IconConfig.() -> Unit) {
        this.disabledIcon = buildIcon(init)
    }

    override fun labelFor(component: Component) {
        labelFor = component
    }

    override fun mnemonic(keyCode: Int) {
        mnemonicSetter = { displayedMnemonic = keyCode }
    }

    override fun mnemonic(character: Char) {
        mnemonicSetter = { setDisplayedMnemonic(character) }
    }

    override fun mnemonicIndex(index: Int) {
        mnemonicIndex = index
    }

    override fun iconTextGap(px: Int) {
        TODO("Not yet implemented")
    }

    override fun textPosition(init: JLabelConfig.Position.() -> Unit) {
        Position({ verticalTextPosition = it }, { horizontalTextPosition = it }).init()
    }

    override fun alignment(init: JLabelConfig.Position.() -> Unit) {
        Position({ verticalAlignment = it }, { horizontalAlignment = it }).init()
    }

    private class Position(val setVertical: (Int) -> Unit, val setHorizontal: (Int) -> Unit) : JLabelConfig.Position {
        override fun top() {
            setVertical(SwingConstants.TOP)
        }

        override fun centerVertically() {
            setVertical(SwingConstants.CENTER)
        }

        override fun bottom() {
            setVertical(SwingConstants.BOTTOM)
        }

        override fun leading() {
            setHorizontal(SwingConstants.LEADING)
        }

        override fun left() {
            setHorizontal(SwingConstants.LEFT)
        }

        override fun centerHorizontally() {
            setHorizontal(SwingConstants.CENTER)
        }

        override fun right() {
            setHorizontal(SwingConstants.RIGHT)
        }

        override fun trailing() {
            setHorizontal(SwingConstants.TRAILING)
        }
    }
}