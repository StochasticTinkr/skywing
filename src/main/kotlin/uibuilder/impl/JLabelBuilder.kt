package com.stoachstictinkr.skywing.uibuilder.impl

import com.stoachstictinkr.skywing.uibuilder.JLabelSpec
import com.stoachstictinkr.skywing.uibuilder.SpecResolver
import javax.swing.Icon
import javax.swing.JLabel
import javax.swing.SwingConstants

class JLabelBuilder : ComponentBuilder<JLabel>(), JLabelSpec {
    private var horizontalAlignment: Int? = null
    private var text: String? = null
    private var icon: Icon? = null
    override fun text(text: String) {
        this.text = text
    }

    override fun icon(icon: Icon) {
        this.icon = icon
    }

    override fun alignLeft() {
        horizontalAlignment = SwingConstants.LEFT
    }

    override fun alignCenter() {
        horizontalAlignment = SwingConstants.CENTER
    }

    override fun alignRight() {
        horizontalAlignment = SwingConstants.RIGHT
    }

    override fun alignLeading() {
        horizontalAlignment = SwingConstants.LEADING
    }

    override fun alignTrailing() {
        horizontalAlignment = SwingConstants.TRAILING
    }

    override fun getInstance(resolver: SpecResolver): JLabel {
        return JLabel(
            text,
            icon,
            horizontalAlignment ?: if (icon != null) SwingConstants.CENTER else SwingConstants.LEADING
        )
    }
}
