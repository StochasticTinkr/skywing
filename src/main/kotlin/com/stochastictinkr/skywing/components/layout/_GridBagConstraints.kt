package com.stochastictinkr.skywing.components.layout

import java.awt.GridBagConstraints
import java.awt.Insets


fun gridBagConstraints(
    gridx: Int = GridBagConstraints.RELATIVE,
    gridy: Int = GridBagConstraints.RELATIVE,
    gridwidth: Int = 1,
    gridheight: Int = 1,
    weightx: Double = 0.0,
    weighty: Double = 0.0,
    anchor: Int = GridBagConstraints.CENTER,
    fill: Int = GridBagConstraints.NONE,
    insets: Insets? = Insets(0, 0, 0, 0),
    ipadx: Int = 0,
    ipady: Int = 0,
) = GridBagConstraints(
    gridx, gridy,
    gridwidth, gridheight,
    weightx, weighty,
    anchor,
    fill,
    insets,
    ipadx, ipady
)

fun GridBagConstraints.copy(
    gridx: Int = this.gridx,
    gridy: Int = this.gridy,
    gridwidth: Int = this.gridwidth,
    gridheight: Int = this.gridheight,
    weightx: Double = this.weightx,
    weighty: Double = this.weighty,
    anchor: Int = this.anchor,
    fill: Int = this.fill,
    insets: Insets? = this.insets,
    ipadx: Int = this.ipadx,
    ipady: Int = this.ipady,
) = gridBagConstraints(gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor, fill, insets, ipadx, ipady)
