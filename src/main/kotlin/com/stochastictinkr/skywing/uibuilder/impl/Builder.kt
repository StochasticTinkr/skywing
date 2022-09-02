package com.stochastictinkr.skywing.uibuilder.impl

internal class Builder<Config, T>(val config: Config, val build: () -> T) {
}

internal fun <Config, BuilderImpl : Config, T> BuilderImpl.asBuilder(build: BuilderImpl.() -> T): Builder<Config, T> =
    Builder(this) { build() }

