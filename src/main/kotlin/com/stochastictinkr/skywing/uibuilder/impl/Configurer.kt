package com.stochastictinkr.skywing.uibuilder.impl

internal class Configurer<Config, T>(val config: Config, val configure: (T) -> Unit) {
    internal fun asBuilder(build: () -> T): Builder<Config, T> = Builder(config) { build().also(configure) }
}

internal fun <Config, ConfigurerImpl : Config, T> ConfigurerImpl.asConfigurer(configure: ConfigurerImpl.(T) -> Unit): Configurer<Config, T> =
    Configurer(this) { configure(it) }

