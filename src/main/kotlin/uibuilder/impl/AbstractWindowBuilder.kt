package com.stoachstictinkr.skywing

import com.stoachstictinkr.skywing.uibuilder.ImagesSpec
import com.stoachstictinkr.skywing.uibuilder.LayoutSpec
import com.stoachstictinkr.skywing.uibuilder.SpecRef
import com.stoachstictinkr.skywing.uibuilder.SpecResolver
import com.stoachstictinkr.skywing.uibuilder.WindowSpec
import com.stoachstictinkr.skywing.uibuilder.impl.AbstractComponentBuilder
import com.stoachstictinkr.skywing.uibuilder.impl.ContainerBuilder
import java.awt.Component
import java.awt.Dialog
import java.awt.Image
import java.awt.Shape
import java.awt.Window

abstract class AbstractWindowBuilder<C : Window> : AbstractComponentBuilder<C>(), WindowSpec<C>,
    LayoutSpec by ContainerBuilder() {
    private var shape: Shape? = null
    private var isDoubleBuffered = false
    private var windowType: Window.Type = Window.Type.NORMAL
    private var isAutoRequestFocus: Boolean = false
    private var isAlwaysOnTop: Boolean = false
    private var iconImages: List<Image> = emptyList()
    private var modalExclusionType: Dialog.ModalExclusionType = Dialog.ModalExclusionType.NO_EXCLUDE
    private var locationSpec: LocationSpec = DefaultLocation
    private var opacity: Float = 1.0f

    override fun alwaysBlockedByModals() {
        modalExclusionType = Dialog.ModalExclusionType.NO_EXCLUDE
    }

    override fun onlyBlockedByChildOrToolkitModals() {
        modalExclusionType = Dialog.ModalExclusionType.APPLICATION_EXCLUDE
    }

    override fun onlyBlockedByChildModals() {
        modalExclusionType = Dialog.ModalExclusionType.TOOLKIT_EXCLUDE
    }

    override fun icons(vararg icons: Image) {
        iconImages = icons.asList()
    }

    override fun icons(icons: ImagesSpec.() -> Unit) {
        iconImages = ImagesSpec(icons).run().toImageList()
    }

    override fun alwaysOnTop() {
        isAlwaysOnTop = true
    }

    override fun notAlwaysOnTop() {
        isAlwaysOnTop = false
    }

    override fun autoFocused() {
        isAutoRequestFocus = true
    }

    override fun notAutoFocused() {
        isAutoRequestFocus = false
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
        locationSpec = CenterLocation
    }

    override fun locationByPlatform() {
        locationSpec = ByPlatformLocation
    }

    override fun locationRelativeTo(relation: SpecRef<out Component>) {
        locationSpec = RelativeLocation(relation)
    }

    override fun doubleBuffered() {
        isDoubleBuffered = true
    }

    override fun windowOpacity(opacity: Float) {
        if (opacity !in 0.0..1.0) {
            throw IllegalArgumentException("window opacity must be between 0.0 and 1.0")
        }
        this.opacity = opacity
    }

    override fun windowShape(shape: Shape) {
        this.shape = shape
    }

    override fun defaultShape() {
        this.shape = null
    }

    private sealed interface LocationSpec {
        fun configure(window: Window, resolver: SpecResolver)
    }

    private object DefaultLocation : LocationSpec {
        override fun configure(window: Window, resolver: SpecResolver) {
        }
    }

    private object ByPlatformLocation : LocationSpec {
        override fun configure(window: Window, resolver: SpecResolver) {
            window.isLocationByPlatform = true
        }
    }

    private object CenterLocation : LocationSpec {
        override fun configure(window: Window, resolver: SpecResolver) {
            window.isLocationByPlatform = false
            window.setLocationRelativeTo(null)
        }
    }

    private class RelativeLocation(val component: SpecRef<out Component>) : LocationSpec {
        override fun configure(window: Window, resolver: SpecResolver) {
            window.isLocationByPlatform = false
            window.setLocationRelativeTo(resolver.resolve(component))
        }
    }

    override fun configure(component: C, resolver: SpecResolver) {
        super.configure(component, resolver)
        configureContainer(component, resolver)
        component.type = windowType
        component.isAutoRequestFocus = isAutoRequestFocus
        component.isAlwaysOnTop = isAlwaysOnTop
        component.iconImages = iconImages
        component.modalExclusionType = modalExclusionType
        component.shape = shape
        configureBeforeOpacity(component, resolver)
        component.opacity = opacity
        configureBeforePacking(component, resolver)
        component.pack()
        configureAfterPacking(component, resolver)
        locationSpec.configure(component, resolver)
    }

    protected open fun configureAfterPacking(component: C, resolver: SpecResolver) {
    }

    protected open fun configureBeforePacking(component: C, resolver: SpecResolver) {
    }

    protected open fun configureBeforeOpacity(component: C, resolver: SpecResolver) {
    }
}