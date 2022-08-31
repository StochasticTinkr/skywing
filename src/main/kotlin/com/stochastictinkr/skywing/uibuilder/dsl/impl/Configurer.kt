package com.stochastictinkr.skywing.uibuilder.dsl.impl

class Configurer<Config, T>(val config: Config, val configure: (T) -> Unit)

fun <Config, Builder : Config, T> Builder.asConfigurer(configure: Builder.(T) -> Unit): Configurer<Config, T> =
    Configurer(this) { configure(it) }

