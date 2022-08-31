package com.stochastictinkr.skywing.uibuilder.dsl


@UiBuilderDsl
interface JFrameConfig : FrameConfig, RootPaneContainer {
    fun doNothingOnClose()
    fun hideOnClose()
    fun disposeOnClose()
    fun exitOnClose()
}
