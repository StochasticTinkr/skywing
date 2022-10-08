package com.stochastictinkr.skywing.swing

object BorderFactory : AbstractBorderFactory() {
    fun title(init: TitleBorderBuilder.()->Unit) = TitleBorderBuilder().apply(init).build()
}
