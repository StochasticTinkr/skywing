package com.stochastictinkr.skywing.components.borders

import java.awt.Color
import javax.swing.border.EtchedBorder

fun etched(
    type: EtchedType = EtchedType.LOWERED,
    highlight: Color? = null,
    shadow: Color? = null,
): EtchedBorder = EtchedBorder(type.value, highlight, shadow)

enum class EtchedType(val value: Int) {
    RAISED(EtchedBorder.RAISED),
    LOWERED(EtchedBorder.LOWERED)
}