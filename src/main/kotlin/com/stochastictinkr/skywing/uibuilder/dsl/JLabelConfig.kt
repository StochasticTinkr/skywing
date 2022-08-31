package com.stochastictinkr.skywing.uibuilder.dsl

import java.awt.Component
import javax.swing.Icon
import javax.swing.JComponent

@UiBuilderDsl
interface JLabelConfig : JComponentConfig {
    fun text(text: String)
    fun icon(icon: Icon)
    fun icon(init: IconConfig.() -> Unit)
    fun disabledIcon(icon: Icon)
    fun disabledIcon(init: IconConfig.() -> Unit)
    fun labelFor(component: Component)
    fun mnemonic(keyCode: Int)
    fun mnemonic(character: Char)
    fun mnemonicIndex(index: Int)
    fun iconTextGap(px: Int)
    fun textPosition(init: Position.() -> Unit)
    fun alignment(init: Position.() -> Unit)

    @UiBuilderDsl
    interface Position {
        fun top()
        fun centerVertically()
        fun bottom()

        fun leading()
        fun left()
        fun centerHorizontally()
        fun right()
        fun trailing()
    }
}

