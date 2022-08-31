package com.stochastictinkr.skywing.uibuilder.dsl.impl

import com.stochastictinkr.skywing.ResourceNotFound
import com.stochastictinkr.skywing.uibuilder.dsl.IconConfig
import java.awt.Image
import java.io.File
import java.net.URL
import javax.swing.Icon
import javax.swing.ImageIcon

fun buildIcon(init: IconConfig.() -> Unit, source: Any = init): Icon? {
    return IconBuilder(source).apply(init).icon
}

private class IconBuilder(val source: Any) : IconConfig {
    var icon: Icon? = null
    override fun icon(icon: Icon) {
        this.icon = icon
    }

    override fun image(image: Image) {
        icon(ImageIcon(image))
    }

    override fun file(filename: String, description: String) {
        icon(ImageIcon(filename, description))
    }

    override fun file(file: File, description: String) {
        url(file.toURI().toURL(), description)
    }

    override fun url(url: URL, description: String) {
        icon(ImageIcon(url, description))
    }

    override fun url(url: String, description: String) {
        url(URL(url), description)
    }

    override fun classResource(resourcePath: String, description: String) {
        val loader = source.javaClass
        url(loader.getResource(resourcePath) ?: throw ResourceNotFound(resourcePath, loader), description)
    }

    override fun classpathResource(resourcePath: String, description: String) {
        val loader = source.javaClass.classLoader
        url(loader.getResource(resourcePath) ?: throw ResourceNotFound(resourcePath, loader), description)
    }
}