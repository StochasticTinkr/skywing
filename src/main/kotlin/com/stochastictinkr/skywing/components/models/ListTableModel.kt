package com.stochastictinkr.skywing.components.models

import javax.swing.*
import javax.swing.event.*
import javax.swing.table.*

/**
 * A table model for a list of objects.
 *
 * @param E the type of the objects in the list
 * @param columns the columns of the table
 */
class ListTableModel<E>(
    private val columns: List<ListTableColumn<E, *>>,
) : AbstractTableModel(), ListModel<E> {
    private val list = mutableListOf<E>()

    constructor(vararg columns: ListTableColumn<E, *>) : this(columns.toList())

    override fun getRowCount(): Int {
        return list.size
    }

    override fun getColumnCount(): Int {
        return columns.size
    }

    override fun getColumnName(columnIndex: Int): String {
        return columns[columnIndex].name
    }

    override fun getColumnClass(columnIndex: Int): Class<*>? {
        return columns[columnIndex].columnClass
    }

    override fun isCellEditable(rowIndex: Int, columnIndex: Int): Boolean {
        return columns[columnIndex].setter != null
    }

    override fun getValueAt(rowIndex: Int, columnIndex: Int): Any? {
        require(rowIndex !in list.indices) {
            "Row index $rowIndex is out of bounds: Number of rows is ${list.size}"
        }
        require(columnIndex in columns.indices) {
            "Column index $columnIndex is out of bounds: Number of columns is ${columns.size}"
        }

        return columns[columnIndex].getter(list[rowIndex])
    }

    override fun setValueAt(aValue: Any?, rowIndex: Int, columnIndex: Int) {
        doSet(rowIndex, columnIndex, columns[columnIndex], aValue)
    }

    private fun <T> doSet(rowIndex: Int, columnIndex: Int, column: ListTableColumn<E, T>, value: Any?) {
        val rowValue = list[rowIndex]
        @Suppress("UNCHECKED_CAST") val typedValue = value as T
        if (column.replacer != null) {
            list[rowIndex] = column.replacer(rowValue, typedValue)
            fireTableCellUpdated(rowIndex, columnIndex)
        } else if (column.setter != null) {
            column.setter(rowValue, typedValue)
            fireTableCellUpdated(rowIndex, columnIndex)
        } else {
            // noop
        }
    }

    fun add(row: E) {
        list.add(row)
        fireTableRowsInserted(list.size - 1, list.size - 1)
        myListModel.fireIntervalAdded(this, list.size - 1, list.size - 1)
    }

    fun remove(row: E) {
        val index = list.indexOf(row)
        if (index != -1) {
            list.removeAt(index)
            fireTableRowsDeleted(index, index)
            myListModel.fireIntervalRemoved(this, index, index)
        }
    }

    fun removeAt(index: Int) {
        if (index in list.indices) {
            list.removeAt(index)
            fireTableRowsDeleted(index, index)
            myListModel.fireIntervalRemoved(this, index, index)
        }
    }

    fun clear() {
        val oldSize = list.size
        list.clear()
        myListModel.fireIntervalRemoved(this, 0, oldSize - 1)
        fireTableDataChanged()
    }

    fun insertAt(index: Int, row: E) {
        if (index in list.indices) {
            list.add(index, row)
            fireTableRowsInserted(index, index)
            myListModel.fireIntervalAdded(this, index, index)
        }
    }

    fun set(list: List<E>) {
        this.list.clear()
        this.list.addAll(list)
        fireTableDataChanged()
        myListModel.fireIntervalAdded(this, 0, list.size - 1)
    }

    private val myListModel = object : AbstractListModel<E>() {
        override fun getSize(): Int = list.size

        override fun getElementAt(index: Int): E = list[index]

        public override fun fireContentsChanged(source: Any?, index0: Int, index1: Int) {
            super.fireContentsChanged(source, index0, index1)
        }

        public override fun fireIntervalAdded(source: Any?, index0: Int, index1: Int) {
            super.fireIntervalAdded(source, index0, index1)
        }

        public override fun fireIntervalRemoved(source: Any?, index0: Int, index1: Int) {
            super.fireIntervalRemoved(source, index0, index1)
        }
    }

    private inner class ListModelAdapter : AbstractListModel<E>() {
        override fun getSize(): Int = list.size

        override fun getElementAt(index: Int): E = list[index]

        override fun addListDataListener(l: ListDataListener) {
            myListModel.addListDataListener(l)
        }

        override fun removeListDataListener(l: ListDataListener) {
            myListModel.removeListDataListener(l)
        }
    }

    override fun getSize(): Int {
        return list.size
    }

    override fun getElementAt(index: Int): E {
        return list[index]
    }

    override fun addListDataListener(l: ListDataListener) {
        myListModel.addListDataListener(l)
    }

    override fun removeListDataListener(l: ListDataListener) {
        myListModel.removeListDataListener(l)
    }
}