package com.stochastictinkr.skywing.uibuilder

import java.awt.Image
import java.io.File
import java.net.URL
import javax.swing.Icon

@UiBuilderDsl
interface IconConfig {
    fun icon(icon: Icon)
    fun image(image: Image)
    fun file(filename: String, description: String = filename)
    fun file(file: File, description: String = file.name)
    fun url(url: URL, description: String = url.toExternalForm())
    fun url(url: String, description: String = url)
    fun classResource(resourcePath: String, description: String = resourcePath)
    fun classpathResource(resourcePath: String, description: String = resourcePath)
}
