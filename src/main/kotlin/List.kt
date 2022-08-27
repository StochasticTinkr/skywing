package com.stoachstictinkr.skywing

import javax.swing.ListModel

fun <E> ListModel<E>.toSequence() = sequence { (0..size).forEach { yield(getElementAt(it)) } }
