package com.stochastictinkr.skywing.uibuilder.dsl

@UiBuilderDsl
interface BorderConfig : BorderConfigSansTitled {
    fun titled(title: String, init: TitledBorderConfig.() -> Unit = {})
}
