package com.stochastictinkr.skywing.uibuilder

import javax.swing.border.TitledBorder

@UiBuilderDsl
interface BorderConfig : BorderConfigSansTitled {
    fun titled(title: String, init: TitledBorderConfig.() -> Unit = {}): TitledBorder
}
