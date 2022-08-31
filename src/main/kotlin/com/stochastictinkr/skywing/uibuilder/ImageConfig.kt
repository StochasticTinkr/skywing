package com.stochastictinkr.skywing.uibuilder

import java.awt.Image
import java.io.File
import java.io.InputStream
import java.net.URL

@UiBuilderDsl
interface ImageConfig {
    fun image(image: Image)
    fun file(filename: String)
    fun file(file: File)
    fun url(url: URL)
    fun url(spec: String)
    fun stream(inputStream: InputStream)
    fun classResource(resourcePath: String)
    fun classpathResource(resourcePath: String)
}