package com.stoachstictinkr.skywing.uibuilder

import com.stoachstictinkr.skywing.uibuilder.impl.JLabelBuilder
import uibuilder.impl.JFrameBuilder
import java.awt.Component
import javax.swing.JFrame

fun frame(init: JFrameSpec.() -> Unit): SpecRef<JFrame> = build(JFrameBuilder(), init)

fun ContainerSpec.label(spec: JLabelSpec.() -> Unit) = buildAndAdd(JLabelBuilder(), spec)
//fun ContainerSpec.box(spec: BoxSpec.() -> Unit) = buildAndAdd(JLabelBuilder(), spec)

private fun <C, B : SpecRef<C>> build(builder: B, spec: B.() -> Unit): SpecRef<C> = builder.apply(spec)
private fun <C : Component, B : SpecRef<C>> ContainerSpec.buildAndAdd(builder: B, spec: B.() -> Unit): SpecRef<C> =
    build(builder, spec).also(::add)

