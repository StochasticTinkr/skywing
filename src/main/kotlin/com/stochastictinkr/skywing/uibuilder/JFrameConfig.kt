package com.stochastictinkr.skywing.uibuilder


@UiBuilderDsl
interface JFrameConfig : FrameConfig, RootPaneContainer, ManagedLayoutConfig {
    fun doNothingOnClose()
    fun hideOnClose()
    fun disposeOnClose()
    fun exitOnClose()
}
