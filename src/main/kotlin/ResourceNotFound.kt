package com.stoachstictinkr.skywing

import java.io.IOException

class ResourceNotFound : IOException {
    constructor(
        resourcePath: String,
        clazz: Class<*>,
    ) : super("Unable to load ${clazz.name} resource \"$resourcePath\"")

    constructor(
        resourcePath: String,
        classLoader: ClassLoader,
    ) : super("Unable to load ${classLoader.name} resource \"$resourcePath\"")
}
