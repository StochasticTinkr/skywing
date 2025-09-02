package com.stochastictinkr.skywing.components.models

import kotlin.reflect.*

data class ListTableColumn<E, T>(
    val name: String = "",
    val getter: (E) -> T,
    val setter: ((E, T) -> Unit)?,
    val replacer: ((E, T) -> E)?,
    val columnClass: Class<*> = Any::class.java,
) {
    constructor(
        name: String = "",
        getter: (E) -> T,
    ) : this(
        name = name,
        getter = getter,
        setter = null,
        replacer = null,
        columnClass = Any::class.java,
    )

    constructor(
        kProperty: KProperty1<E, T>,
        name: String = kProperty.name,
        getter: (E) -> T = { kProperty.get(it) },
        setter: ((E, T) -> Unit)? = null,
        replacer: ((E, T) -> E)? = null,
        columnClass: Class<*> = (kProperty.returnType.classifier as KClass<*>).java,
    ) : this(
        name = name,
        getter = getter,
        setter = setter,
        replacer = replacer,
        columnClass = columnClass,
    )

    constructor(
        kMutableProperty: KMutableProperty1<E, T>,
        name: String = kMutableProperty.name,
        getter: (E) -> T = { kMutableProperty.get(it) },
        setter: ((E, T) -> Unit)? = { e, t -> kMutableProperty.set(e, t) },
        replacer: ((E, T) -> E)? = null,
        columnClass: Class<*> = (kMutableProperty.returnType.classifier as KClass<*>).java,
    ) : this(
        name = name,
        getter = getter,
        setter = setter,
        replacer = replacer,
        columnClass = columnClass,
    )
}