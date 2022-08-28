package com.stoachstictinkr.skywing.uibuilder

import java.awt.Image
import java.io.File
import java.net.URL
import javax.swing.Icon

@UiBuilderDsl
interface IconSpec {
    fun icon(icon: Icon): Icon
    fun image(image: Image): Icon
    fun file(filename: String, description: String = filename): Icon
    fun file(file: File, description: String? = null): Icon
    fun url(url: URL, description: String? = null): Icon
    fun url(spec: String, description: String? = null): Icon
    fun classResource(resourcePath: String): Icon
    fun classpathResource(resourcePath: String): Icon
}
