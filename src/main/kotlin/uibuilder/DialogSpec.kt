package com.stochastictinkr.skywing.uibuilder

import java.awt.Dialog

interface DialogSpec<C : Dialog> : WindowSpec<C> {
    fun title(title: String)
    fun nonModal()
    fun documentModal()
    fun applicationModal()
    fun toolkitModal()
    fun notResizable()
    fun resizable()
    fun undecorated()
    fun decorated()
}
