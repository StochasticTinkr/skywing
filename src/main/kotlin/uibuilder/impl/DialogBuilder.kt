package com.stochastictinkr.skywing.uibuilder.impl

import com.stochastictinkr.skywing.uibuilder.SpecRef
import com.stochastictinkr.skywing.uibuilder.SpecResolver
import java.awt.Dialog
import java.awt.Window

class DialogBuilder : AbstractDialogBuilder<Dialog>() {
    private var owner: SpecRef<out Window>? = null

    override fun getInstance(resolver: SpecResolver): Dialog {
        return Dialog(owner?.let(resolver::resolve))
    }
}