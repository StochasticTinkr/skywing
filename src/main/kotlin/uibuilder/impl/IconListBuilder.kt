package com.stochastictinkr.skywing.uibuilder.impl

import javax.swing.Icon

class IconListBuilder(clazz: Class<*>) : IconBuilder(clazz) {
    private val icons = mutableListOf<Icon>()
    override fun icon(icon: Icon) = icon.also(icons::add)
}