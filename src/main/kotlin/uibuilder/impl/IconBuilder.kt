package com.stoachstictinkr.skywing.uibuilder.impl

import com.stoachstictinkr.skywing.ResourceNotFound
import com.stoachstictinkr.skywing.uibuilder.IconSpec
import java.awt.Image
import java.io.File
import java.net.URL
import javax.swing.Icon
import javax.swing.ImageIcon

open class IconBuilder(private val clazz: Class<*>) : IconSpec {
    override fun icon(icon: Icon) = icon
    override fun image(image: Image) = icon(ImageIcon(image))
    override fun file(filename: String, description: String) = icon(ImageIcon(filename, description))
    override fun file(file: File, description: String?) =
        icon(ImageIcon(file.toURI().toURL(), description ?: file.toURI().toURL().toExternalForm()))

    override fun url(url: URL, description: String?) = icon(ImageIcon(url, description ?: url.toExternalForm()))

    override fun url(spec: String, description: String?) = url(URL(spec), description)

    override fun classResource(resourcePath: String) =
        url(clazz.getResource(resourcePath) ?: throw ResourceNotFound(resourcePath, clazz))

    override fun classpathResource(resourcePath: String) =
        url(clazz.classLoader.getResource(resourcePath) ?: throw ResourceNotFound(resourcePath, clazz.classLoader))
}