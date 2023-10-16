package com.stochastictinkr.skywing.utils

import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty0
import kotlin.reflect.KProperty1
import kotlin.reflect.jvm.isAccessible

data class ObservableProperty<V>(
    val get: () -> V,
    val set: (V) -> Unit = {},
    val addListener: ((V) -> Unit) -> Disposable = { NoOpDisposable },
) {
    operator fun provideDelegate(thisRef: Any, property: KProperty<*>) =
        ObservablePropertyDelegate(this)

    operator fun provideDelegate(thisRef: Nothing?, property: KProperty<*>) =
        ObservablePropertyDelegate(this)
}

class ObservablePropertyDelegate<V>(val observableProperty: ObservableProperty<V>) {
    operator fun getValue(thisRef: Any, property: KProperty<*>) = observableProperty.get()
    operator fun setValue(thisRef: Any, property: KProperty<*>, value: V) = observableProperty.set(value)

    operator fun getValue(thisRef: Nothing?, property: KProperty<*>) = observableProperty.get()
    operator fun setValue(thisRef: Nothing?, property: KProperty<*>, value: V) = observableProperty.set(value)

}

fun <V> ObservableProperty<V>.asBoolean(trueValue: V, falseValue: V, unmatchedIs: Boolean = false) =
    convertingFrom(fromBooleanConverter(trueValue, falseValue, unmatchedIs))

fun <T, U> ObservableProperty<U>.convertingFrom(converter: Converter<T, U>): ObservableProperty<T> =
    ObservableProperty({ converter.revert(get()) },
        { set(converter.convert(it)) },
        { listener -> addListener { listener(converter.revert(get())) } })

fun ObservableProperty<Double>.convertingTo(scale: Scaling): ObservableProperty<Double> =
    convertingFrom(scale.asConverter())

@Suppress("UNCHECKED_CAST")
fun <V> KProperty0<V>.asObservable() =
    run {
        isAccessible = true
        (getDelegate() as? ObservablePropertyDelegate<V>)?.observableProperty ?: ObservableProperty({ get() })
    }

@Suppress("UNCHECKED_CAST")
fun <V> KMutableProperty0<V>.asObservable() =
    run {
        isAccessible = true
        (getDelegate() as? ObservablePropertyDelegate<V>)?.observableProperty ?: ObservableProperty(
            { get() },
            { set(it) })
    }

@Suppress("UNCHECKED_CAST")
fun <T, V> KProperty1<T, V>.asObservable(obj: T) =
    run {
        isAccessible = true
        (getDelegate(obj) as? ObservablePropertyDelegate<V>)?.observableProperty ?: ObservableProperty({ get(obj) })
    }

@Suppress("UNCHECKED_CAST")
fun <T, V> KMutableProperty1<T, V>.asObservable(obj: T) =
    run {
        isAccessible = true

        (getDelegate(obj) as? ObservablePropertyDelegate<V>)?.observableProperty ?: ObservableProperty(
            { get(obj) },
            { set(obj, it) })
    }

fun ObservableProperty<Double>.asInt(): ObservableProperty<Int> = convertingFrom(DoubleToInt)

fun <V> observable(initial: V): ObservableProperty<V> {
    var field = initial
    val listeners = mutableListOf<(V) -> Unit>()
    return ObservableProperty(
        get = { field },
        set = { value ->
            if (value != field) {
                field = value
                listeners.forEach { listener -> listener(field) }
            }
        },
        addListener = { listener ->
            listeners.add(listener)
            Disposable { listeners.remove(listener) }
        })
}
