package com.stoachstictinkr.skywing

import com.stoachstictinkr.skywing.uibuilder.JFrameSpec
import uibuilder.impl.JFrameBuilder

fun frame(init: JFrameSpec.() -> Unit) =
    JFrameBuilder().apply(init)
