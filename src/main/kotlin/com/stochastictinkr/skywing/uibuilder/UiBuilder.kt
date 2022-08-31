package com.stochastictinkr.skywing.uibuilder

import com.stochastictinkr.skywing.uibuilder.dsl.JFrameConfig
import com.stochastictinkr.skywing.uibuilder.dsl.impl.jFrameConfigurer
import javax.swing.JFrame

fun frame(init: JFrameConfig.() -> Unit): JFrame {
    return JFrame().also {
        jFrameConfigurer()
            .apply { config.init() }
            .configure(it)
    }
}
