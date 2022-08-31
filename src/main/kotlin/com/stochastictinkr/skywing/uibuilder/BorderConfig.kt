package com.stochastictinkr.skywing.uibuilder

@UiBuilderDsl
interface BorderConfig : BorderConfigSansTitled {
    fun titled(title: String, init: TitledBorderConfig.() -> Unit = {})
}
