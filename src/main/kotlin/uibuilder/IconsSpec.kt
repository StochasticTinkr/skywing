package com.stoachstictinkr.skywing.uibuilder

import com.stoachstictinkr.skywing.ResourceNotFound
import com.stoachstictinkr.skywing.UiBuilderDsl
import java.awt.Image
import java.io.File
import java.net.URL
import javax.swing.Icon
import javax.swing.ImageIcon

@UiBuilderDsl
open class IconSpec(private val clazz: Class<*> = IconSpec::class.java) {
    open fun icon(icon: Icon) = icon
    fun image(image: Image) = icon(ImageIcon(image))
    fun file(filename: String, description: String = filename) = icon(ImageIcon(filename, description))
    fun file(file: File, description: String? = null) =
        icon(ImageIcon(file.toURI().toURL(), description ?: file.toURI().toURL().toExternalForm()))

    fun url(url: URL, description: String? = null) = icon(ImageIcon(url, description ?: url.toExternalForm()))

    fun url(spec: String, description: String? = null) = url(URL(spec), description)

    fun classResource(resourcePath: String) =
        url(clazz.getResource(resourcePath) ?: throw ResourceNotFound(resourcePath, clazz))

    fun classpathResource(resourcePath: String) =
        url(clazz.classLoader.getResource(resourcePath) ?: throw ResourceNotFound(resourcePath, clazz.classLoader))
}

@UiBuilderDsl
class IconsSpec(clazz: Class<*>) : IconSpec(clazz) {
    private val icons = mutableListOf<Icon>()
    override fun icon(icon: Icon) = icon.also(icons::add)
}
