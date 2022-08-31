package com.stochastictinkr.skywing.uibuilder

import java.awt.Component
import java.awt.Dialog.ModalExclusionType
import java.awt.Shape
import java.awt.Image as AwtImage

@UiBuilderDsl
interface WindowConfig : ContainerConfig {
    fun windowOpacity(opacity: Float)
    fun windowShape(shape: Shape)
    fun defaultShape()
    fun modalExclusions(type: ModalExclusionType)
    fun icons(vararg icons: AwtImage)
    fun icons(init: ImageConfig.() -> Unit)
    fun alwaysOnTop()
    fun notAlwaysOnTop()
    fun autoFocused()
    fun notAutoFocused()
    fun normalWindow()
    fun utilityWindow()
    fun popupWindow()
    fun centeredOnScreen()
    fun locationByPlatform()
    fun locationRelativeTo(relation: Component)
    fun packed()
    fun doubleBuffered()

    companion object {
        var NO_EXCLUDE: ModalExclusionType = ModalExclusionType.NO_EXCLUDE
        var APPLICATION_EXCLUDE: ModalExclusionType = ModalExclusionType.APPLICATION_EXCLUDE
        var TOOLKIT_EXCLUDE: ModalExclusionType = ModalExclusionType.TOOLKIT_EXCLUDE
    }
}