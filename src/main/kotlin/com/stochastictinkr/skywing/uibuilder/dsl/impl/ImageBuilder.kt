package com.stochastictinkr.skywing.uibuilder.dsl.impl

import com.stochastictinkr.skywing.ResourceNotFound
import com.stochastictinkr.skywing.uibuilder.dsl.ImageConfig
import java.awt.Image
import java.io.File
import java.io.InputStream
import java.net.URL
import javax.imageio.ImageIO

fun buildImage(init: ImageConfig.() -> Unit, source: Any = init): Image? {
    return ImageBuilder(source).apply(init).image
}

fun buildImages(init: ImageConfig.() -> Unit, source: Any = init): List<Image> {
    return ImageListBuilder(source).apply(init).images
}

private class ImageBuilder(source: Any) : AbstractImageBuilder(source) {
    var image: Image? = null
    override fun image(image: Image) {
        this.image = image
    }
}
private class ImageListBuilder(source: Any) : AbstractImageBuilder(source) {
    var images = mutableListOf<Image>()
    override fun image(image: Image) {
        images.add(image)
    }
}
private abstract class AbstractImageBuilder(val source: Any) : ImageConfig {
    override fun file(filename: String) {
        file(File(filename))
    }

    override fun file(file: File) {
        image(ImageIO.read(file))
    }

    override fun url(url: URL) {
        image(ImageIO.read(url))
    }

    override fun url(spec: String) {
        url(URL(spec))
    }

    override fun stream(inputStream: InputStream) {
        image(ImageIO.read(inputStream))
    }

    override fun classResource(resourcePath: String) {
        val loader = source.javaClass
        url(loader.getResource(resourcePath) ?: throw ResourceNotFound(resourcePath, loader))
    }

    override fun classpathResource(resourcePath: String) {
        val loader = source.javaClass.classLoader
        url(loader.getResource(resourcePath) ?: throw ResourceNotFound(resourcePath, loader))
    }

}