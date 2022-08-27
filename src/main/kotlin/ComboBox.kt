package com.stoachstictinkr.skywing

import javax.swing.JComboBox

fun <E> comboBox(init: JComboBox<E>.() -> Unit) =
    JComboBox<E>().apply(init)
