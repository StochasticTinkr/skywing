package com.stochastictinkr.skywing.models

import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty

fun <T> T.listeners() = Listeners<T>()

class Listeners<T> {
    private val listenersByProperty: MutableMap<String, Listeners<*>> = mutableMapOf()

    private class Listeners<V> {
        val observers: MutableList<(V) -> Unit> = mutableListOf()
        val vetoers: MutableList<(V) -> Boolean> = mutableListOf()
    }

    fun <V> onChange(property: KMutableProperty1<in T, V>, observer: (V) -> Unit) = onChange(property.name, observer)

    fun <V> onChange(propertyName: String, observer: (V) -> Unit) {
        getOrCreateListenersFor<V>(propertyName).observers.add(observer)
    }

    fun <V> vetoChangeIf(property: KMutableProperty1<in T, V>, vetoer: (V) -> Boolean) =
        vetoChangeIf(property.name, vetoer)

    fun <V> vetoChangeIf(propertyName: String, vetoer: (V) -> Boolean) {
        getOrCreateListenersFor<V>(propertyName).vetoers.add(vetoer)
    }

    @Suppress("UNCHECKED_CAST")
    private fun <V> getOrCreateListenersFor(propertyName: String) =
        listenersByProperty.computeIfAbsent(propertyName) { Listeners<V>() } as Listeners<V>

    @Suppress("UNCHECKED_CAST")
    private fun <V> getListeners(propertyName: String) =
        listenersByProperty[propertyName] as Listeners<V>?

    fun <V> property(default: V) =
        object {
            var value: V = default
            operator fun getValue(thisRef: T, property: KProperty<*>) = value

            operator fun setValue(thisRef: T, property: KProperty<*>, newValue: V) {
                if (newValue != value) {
                    val listeners = getListeners<V>(property.name)

                    if (listeners?.vetoers?.all { it(newValue) } == false) {
                        return
                    }
                    value = newValue
                    listeners?.observers?.forEach { it(value) }
                }
            }
        }
}