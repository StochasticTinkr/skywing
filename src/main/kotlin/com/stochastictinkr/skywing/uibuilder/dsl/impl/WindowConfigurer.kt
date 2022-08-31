package com.stochastictinkr.skywing.uibuilder.dsl.impl

import com.stochastictinkr.skywing.uibuilder.dsl.ComponentConfig
import com.stochastictinkr.skywing.uibuilder.dsl.ImageConfig
import com.stochastictinkr.skywing.uibuilder.dsl.ManagedLayoutConfig
import com.stochastictinkr.skywing.uibuilder.dsl.WindowConfig
import java.awt.Component
import java.awt.Container
import java.awt.Dialog.ModalExclusionType
import java.awt.Image
import java.awt.Shape
import java.awt.Window

fun windowConfigurer(): Configurer<WindowConfig, Window> =
    WindowConfigurer().asConfigurer { window ->
        componentConfigurer.configure(window)
        managedLayoutConfigurer.configure(window)
        window.shape = shape
        window.type = windowType
        window.iconImages = iconImages
        window.modalExclusionType = modalExclusionType
        window.isAlwaysOnTop = alwaysOnTop
        window.isAutoRequestFocus = autoFocused
        window.createBufferStrategy(if (doubleBuffered) 2 else 1)
        window.opacity = opacity

        if (packed) {
            window.pack()
        }
        configureLocation(window)
    }

private class WindowConfigurer(
    val componentConfigurer: Configurer<ComponentConfig, Component> = componentConfigurer(),
    val managedLayoutConfigurer: Configurer<ManagedLayoutConfig, in Container> = containerConfigurer(),
) : WindowConfig, ComponentConfig by componentConfigurer.config, ManagedLayoutConfig by managedLayoutConfigurer.config {
    var windowType = Window.Type.NORMAL
    var packed: Boolean = false
    var opacity: Float = 1.0f
    var shape: Shape? = null
    val iconImages = mutableListOf<Image>()
    var modalExclusionType: ModalExclusionType = ModalExclusionType.TOOLKIT_EXCLUDE
    var alwaysOnTop = false
    var autoFocused = false
    var doubleBuffered: Boolean = false
    var configureLocation: Window.() -> Unit = { }

    override fun windowOpacity(opacity: Float) {
        this.opacity = opacity
    }

    override fun windowShape(shape: Shape) {
        this.shape = shape
    }

    override fun defaultShape() {
        shape = null
    }

    override fun modalExclusions(type: ModalExclusionType) {
        this.modalExclusionType = type
    }

    override fun icons(vararg icons: Image) {
        iconImages.addAll(icons)
    }

    override fun icons(init: ImageConfig.() -> Unit) {
        iconImages.addAll(buildImages(init))
    }

    override fun alwaysOnTop() {
        alwaysOnTop = true
    }

    override fun notAlwaysOnTop() {
        alwaysOnTop = false
    }

    override fun autoFocused() {
        autoFocused = true
    }

    override fun notAutoFocused() {
        autoFocused = false
    }

    override fun normalWindow() {
        windowType = Window.Type.NORMAL
    }

    override fun utilityWindow() {
        windowType = Window.Type.UTILITY
    }

    override fun popupWindow() {
        windowType = Window.Type.POPUP
    }

    override fun centeredOnScreen() {
        configureLocation = {
            setLocationRelativeTo(null)
            isLocationByPlatform = false
        }
    }

    override fun locationByPlatform() {
        configureLocation = {
            isLocationByPlatform = true
        }
    }

    override fun locationRelativeTo(relation: Component) {
        configureLocation = {
            setLocationRelativeTo(relation)
            isLocationByPlatform = false
        }
    }

    override fun packed() {
        packed = true
    }

    override fun doubleBuffered() {
        doubleBuffered = true
    }
}