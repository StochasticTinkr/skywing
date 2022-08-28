package com.stoachstictinkr.skywing.uibuilder

import java.awt.Component
import java.awt.Image
import java.awt.Shape
import java.awt.Window

@UiBuilderDsl
interface WindowSpec<C : Window> : ComponentSpec<C>, ContainerSpec, LayoutSpec {
    fun windowOpacity(opacity: Float)
    fun windowShape(shape: Shape)
    fun defaultShape()
    fun alwaysBlockedByModals()
    fun onlyBlockedByChildOrToolkitModals()
    fun onlyBlockedByChildModals()
    fun icons(vararg icons: Image)
    fun icons(icons: ImagesSpec.() -> Unit)
    fun alwaysOnTop()
    fun notAlwaysOnTop()
    fun autoFocused()
    fun notAutoFocused()
    fun normalWindow()
    fun utilityWindow()
    fun popupWindow()
    fun centeredOnScreen()
    fun locationByPlatform()
    fun locationRelativeTo(relation: SpecRef<out Component>)
    fun doubleBuffered()
}