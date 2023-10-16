package com.stochastictinkr.skywing.components.events

import com.stochastictinkr.skywing.toolkit.toolkit
import com.stochastictinkr.skywing.utils.containsAllBitsIn
import java.awt.event.InputEvent

val InputEvent.isMenuShortcutDown get() = modifiersEx containsAllBitsIn toolkit.menuShortcutKeyMaskEx

