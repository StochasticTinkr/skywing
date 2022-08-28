package com.stoachstictinkr.skywing.uibuilder.impl

import com.stoachstictinkr.skywing.AbstractWindowBuilder
import com.stoachstictinkr.skywing.uibuilder.DialogSpec
import com.stoachstictinkr.skywing.uibuilder.SpecResolver
import java.awt.Dialog

abstract class AbstractDialogBuilder<C : Dialog> : AbstractWindowBuilder<C>(), DialogSpec<C> {
    private var modalityType: Dialog.ModalityType = Dialog.ModalityType.MODELESS
    private var isResizable = true
    private var isUndecorated = false
    private var title = ""

    override fun title(title: String) {
        this.title = title
    }

    override fun nonModal() {
        modalityType = Dialog.ModalityType.MODELESS
    }

    override fun documentModal() {
        modalityType = Dialog.ModalityType.DOCUMENT_MODAL
    }

    override fun applicationModal() {
        modalityType = Dialog.ModalityType.APPLICATION_MODAL
    }

    override fun toolkitModal() {
        modalityType = Dialog.ModalityType.TOOLKIT_MODAL
    }

    override fun notResizable() {
        isResizable = false
    }

    override fun resizable() {
        isResizable = true
    }

    override fun undecorated() {
        isUndecorated = true
    }

    override fun decorated() {
        isUndecorated = false
    }

    override fun configure(component: C, resolver: SpecResolver) {
        component.title = title
        component.modalityType = modalityType
        component.isUndecorated = isUndecorated
        component.isResizable = isResizable
        super.configure(component, resolver)
    }
}

