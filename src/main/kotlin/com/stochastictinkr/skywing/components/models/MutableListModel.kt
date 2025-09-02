package com.stochastictinkr.skywing.components.models

import javax.swing.*

fun <E> DefaultListModel<E>.asMutableList(): MutableList<E> {
    return DefaultListWrapper(this)
}

private class DefaultListWrapper<E>(private val outer: DefaultListModel<E>) : AbstractMutableList<E>(), RandomAccess {
    override fun add(index: Int, element: E) {
        outer.add(index, element)
    }

    override fun removeAt(index: Int): E {
        val element = outer[index]
        outer.remove(index)
        return element
    }

    override fun set(index: Int, element: E): E {
        val oldElement = outer[index]
        outer[index] = element
        return oldElement
    }

    override val size: Int get() = outer.size

    override fun get(index: Int): E = outer[index]

    override fun clear() {
        outer.clear()
    }

    override fun remove(element: E): Boolean = outer.removeElement(element)

    override fun add(element: E): Boolean {
        outer.addElement(element)
        return true
    }

    override fun addAll(index: Int, elements: Collection<E>): Boolean {
        outer.addAll(index, elements)
        return elements.isNotEmpty()
    }

    override fun removeAll(elements: Collection<E>): Boolean {
        // acc on the right side to avoid short circuit, so that all elements are removed
        return elements.fold(false) { acc, element -> outer.removeElement(element) || acc }
    }

    override fun retainAll(elements: Collection<E>): Boolean {
        val it = listIterator()
        var changed = false
        while (it.hasNext()) {
            val element = it.next()
            if (element !in elements) {
                it.remove()
                changed = true
            }
        }
        return changed
    }

    override fun toArray(): Array<out Any?> = outer.toArray()

}