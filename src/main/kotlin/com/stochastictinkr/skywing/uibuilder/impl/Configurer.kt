package com.stochastictinkr.skywing.uibuilder.impl

internal class Configurer<Config, T>(val config: Config, val configure: (T) -> Unit)

internal fun <Config, Builder : Config, T> Builder.asConfigurer(configure: Builder.(T) -> Unit): Configurer<Config, T> =
    Configurer(this) { configure(it) }

