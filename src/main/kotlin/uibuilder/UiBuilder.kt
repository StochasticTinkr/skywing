package com.stochastictinkr.skywing.uibuilder

import com.stochastictinkr.skywing.uibuilder.impl.JLabelBuilder
import com.stochastictinkr.skywing.uibuilder.impl.ObjectRef
import com.stochastictinkr.skywing.uibuilder.impl.JFrameBuilder
import java.awt.Color
import java.awt.Component
import javax.swing.JFrame

fun frame(init: JFrameSpec.() -> Unit): SpecRef<JFrame> = build(JFrameBuilder(), init)

fun ContainerSpec.label(spec: JLabelSpec.() -> Unit) = buildAndAdd(JLabelBuilder(), spec)
//fun ContainerSpec.box(spec: BoxSpec.() -> Unit) = buildAndAdd(JLabelBuilder(), spec)

fun <C> ref(obj: C): SpecRef<C> = ObjectRef(obj)

private fun <C, B : SpecRef<C>> build(builder: B, spec: B.() -> Unit): SpecRef<C> = builder.apply(spec)
private fun <C : Component, B : SpecRef<C>> ContainerSpec.buildAndAdd(builder: B, spec: B.() -> Unit): SpecRef<C> =
    build(builder, spec).also(::add)

fun rgb(red: Int, green: Int, blue: Int, alpha: Int = 255) = Color(red, green, blue, alpha)
fun rgb(red: Float, green: Float, blue: Float, alpha: Float = 1.0f) = Color(red, green, blue, alpha)
fun hsb(hue: Float, saturation: Float, brightness: Float) = Color.getHSBColor(hue, saturation, brightness)

