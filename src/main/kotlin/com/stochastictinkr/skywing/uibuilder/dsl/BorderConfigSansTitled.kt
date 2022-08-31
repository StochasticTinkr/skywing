package com.stochastictinkr.skywing.uibuilder.dsl

import java.awt.Color

@UiBuilderDsl
interface BorderConfigSansTitled {
    // Line borders
    fun line(color: Color, thickness: Int = 1, rounded: Boolean = false)
    fun line(thickness: Int = 1, rounded: Boolean = false, init: ColorConfig.() -> Unit)

    // Bevel borders
    fun bevel(init: BevelConfig.() -> Unit)
    fun softBevel(init: BevelConfig.() -> Unit) = bevel { soft(); init() }
    fun loweredBevel(init: BevelConfig.() -> Unit = {}) = bevel { lowered(); init() }
    fun raisedBevel(init: BevelConfig.() -> Unit = {}) = bevel { raised(); init() }
    fun loweredBevel(highlight: Color, shadow: Color) = loweredBevel { withColors(highlight, shadow) }
    fun raisedBevel(highlight: Color, shadow: Color) = raisedBevel { withColors(highlight, shadow) }
    fun loweredBevel(
        highlightOuter: Color, highlightInner: Color,
        shadowOuter: Color, shadowInner: Color,
    ) = loweredBevel { withColors(highlightOuter, highlightInner, shadowOuter, shadowInner) }

    fun raisedBevel(
        highlightOuter: Color, highlightInner: Color,
        shadowOuter: Color, shadowInner: Color,
    ) = raisedBevel { withColors(highlightOuter, highlightInner, shadowOuter, shadowInner) }

    fun loweredSoftBevel(init: BevelConfig.() -> Unit = {}) = softBevel { lowered(); init() }
    fun raisedSoftBevel(init: BevelConfig.() -> Unit = {}) = softBevel { raised(); init() }
    fun loweredSoftBevel(highlight: Color, shadow: Color) = loweredSoftBevel { withColors(highlight, shadow) }
    fun raisedSoftBevel(highlight: Color, shadow: Color) = raisedSoftBevel { withColors(highlight, shadow) }
    fun loweredSoftBevel(
        highlightOuter: Color, highlightInner: Color,
        shadowOuter: Color, shadowInner: Color,
    ) = loweredSoftBevel { withColors(highlightOuter, highlightInner, shadowOuter, shadowInner) }

    fun raisedSoftBevel(
        highlightOuter: Color, highlightInner: Color,
        shadowOuter: Color, shadowInner: Color,
    ) = raisedSoftBevel { withColors(highlightOuter, highlightInner, shadowOuter, shadowInner) }


    // Etched borders
    fun etched(init: EtchedConfig.() -> Unit = {})
    fun loweredEtched(init: EtchedConfig.() -> Unit = {}) = etched { lowered(); init() }
    fun raisedEtched(init: EtchedConfig.() -> Unit = {}) = etched { raised(); init() }
    fun loweredEtched(highlight: Color, shadow: Color) = loweredEtched { highlight(highlight); shadow(shadow) }
    fun raisedEtched(highlight: Color, shadow: Color) = raisedEtched { highlight(highlight); shadow(shadow) }

}