package com.stoachstictinkr.skywing.uibuilder

import com.stoachstictinkr.skywing.ResourceNotFound
import com.stoachstictinkr.skywing.UiBuilderDsl
import java.awt.Image
import java.io.File
import java.io.InputStream
import java.net.URL
import javax.imageio.ImageIO

@UiBuilderDsl
class ImagesSpec(private val builder: ImagesSpec.() -> Unit) {
    private val images = mutableListOf<Image>()
    fun run() = apply(builder)

    fun image(image: Image) {
        images.add(image)
    }

    fun file(filename: String) {
        file(File(filename))
    }

    fun file(file: File) {
        image(ImageIO.read(file))
    }

    fun url(url: URL) {
        image(ImageIO.read(url))
    }

    fun url(spec: String) {
        image(ImageIO.read(URL(spec)))
    }

    fun stream(inputStream: InputStream) {
        image(ImageIO.read(inputStream))
    }

    fun classResource(resourcePath: String) {
        val loader = builder.javaClass
        url(loader.getResource(resourcePath) ?: throw ResourceNotFound(resourcePath, loader))
    }

    fun classpathResource(resourcePath: String) {
        val loader = builder.javaClass.classLoader
        url(loader.getResource(resourcePath) ?: throw ResourceNotFound(resourcePath, loader))
    }

    fun toImageList() = images
    fun toImageIconList() = images
}