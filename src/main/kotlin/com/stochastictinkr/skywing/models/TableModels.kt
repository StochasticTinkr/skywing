package com.stochastictinkr.skywing.models
//
//import javax.swing.ListModel
//import javax.swing.event.ListDataEvent
//import javax.swing.event.ListDataListener
//import javax.swing.table.AbstractTableModel
//import kotlin.reflect.KMutableProperty1
//import kotlin.reflect.KProperty1
//
//
//interface Columns<T> {
//    val numberOfColumns: Int
//    fun getName(index: Int): String
//    fun getType(index: Int): Class<*>
//    fun getFrom(index: Int, item: T): Any?
//    fun isReadOnly(index: Int, item: T): Boolean
//    fun setTo(index: Int, item: T, value: Any?)
//}
//
//interface Column<T> {
//    val name: String
//    val type: Class<*>
//    fun getFrom(item: T): Any?
//    fun isReadOnly(item: T): Boolean
//    fun setTo(item: T, value: Any?)
//}
//
//class DefaultColumn<T, V>(
//    override val name: String,
//    override val type: Class<V>,
//    private val get: T.() -> V,
//    private val isReadOnly: T.() -> Boolean,
//    private val set: T.(V) -> Unit,
//) : Column<T> {
//    override fun getFrom(item: T) = item.get()
//
//    override fun isReadOnly(item: T) = item.isReadOnly()
//
//    override fun setTo(item: T, value: Any?) = item.set(type.cast(value))
//}
//
//
//interface ColumnsConfig<T> {
//    fun <V> column(
//        name: String,
//        type: Class<V>,
//        get: T.() -> V,
//        isReadOnly: T.() -> Boolean,
//        set: T.(V) -> Unit,
//    )
//
//    fun column(column: Column<T>)
//    fun column(init: ColumnFactory<T>.() -> Unit)
//}
//
//fun <T> columns(init: ColumnsConfig<T>.() -> Unit): Columns<T> =
//    ColumnsConfigurer<T>().run {
//        init()
//        ColumnList(columns.toList())
//    }
//
//
//private class ColumnsConfigurer<T> : ColumnsConfig<T> {
//    val columns = mutableListOf<Column<in T>>()
//    override fun <V> column(
//        name: String,
//        type: Class<V>,
//        get: T.() -> V,
//        isReadOnly: T.() -> Boolean,
//        set: T.(V) -> Unit,
//    ) = column(DefaultColumn(name, type, get, isReadOnly, set))
//
//    override fun column(column: Column<T>) {
//        columns.add(column)
//    }
//
//    override fun column(init: ColumnFactory<T>.() -> Unit): Unit = column(ColumnFactory<T>().apply(init).column!!)
//}
//
//inline fun <T, reified V> ColumnsConfig<T>.column(property: KProperty1<T, V>, name: String = property.name) {
//    column(name, V::class.java, property::get, { true }, {})
//}
//
//class ColumnFactory<T> {
//    internal var column: Column<T>? = null
//}
//
//class TypedColumnFactory<T, V>(val name: String, val type: Class<V>) {
//}
//
//inline fun <T, reified V> ColumnFactory<T>.getter(noinline get: T.() -> V):TypedColumnFactory<T, V> {
//
//}
//
//fun <T, reified V> ColumnsConfig<T>.column(property: KMutableProperty1<T, V>, name: String = property.name) {
//    column(name, V::class.java, property::get, { false }, property::set)
//}
//
//class ColumnList<T>(private val columns: List<Column<in T>>) : Columns<T> {
//    override val numberOfColumns get() = columns.size
//
//    override fun getName(index: Int) = columns[index].name
//    override fun getType(index: Int) = columns[index].type
//    override fun getFrom(index: Int, item: T) = columns[index].getFrom(item)
//    override fun isReadOnly(index: Int, item: T) = columns[index].isReadOnly(item)
//    override fun setTo(index: Int, item: T, value: Any?) = columns[index].setTo(item, value)
//}
//
//class ListTableModel<T>(private val columns: Columns<T>, private val list: ListModel<T>) : AbstractTableModel() {
//    init {
//        list.addListDataListener(object : ListDataListener {
//            override fun intervalAdded(e: ListDataEvent) {
//                fireTableRowsInserted(e.index0, e.index1)
//            }
//
//            override fun intervalRemoved(e: ListDataEvent) {
//                fireTableRowsDeleted(e.index0, e.index1)
//            }
//
//            override fun contentsChanged(e: ListDataEvent) {
//                fireTableRowsUpdated(e.index0, e.index1)
//            }
//        })
//    }
//
//    override fun getRowCount(): Int = list.size
//
//    override fun getColumnCount() = columns.numberOfColumns
//    override fun getColumnName(column: Int): String = columns.getName(column)
//
//    override fun getValueAt(rowIndex: Int, columnIndex: Int): Any? = columns.getFrom(columnIndex, row(rowIndex))
//
//    override fun isCellEditable(rowIndex: Int, columnIndex: Int) = !columns.isReadOnly(columnIndex, row(rowIndex))
//    override fun setValueAt(value: Any?, rowIndex: Int, columnIndex: Int) {
//        columns.setTo(columnIndex, row(rowIndex), value)
//        fireTableCellUpdated(rowIndex, columnIndex)
//    }
//
//    override fun getColumnClass(columnIndex: Int): Class<*> = columns.getType(columnIndex)
//    private fun row(rowIndex: Int): T = list.getElementAt(rowIndex)
//}
