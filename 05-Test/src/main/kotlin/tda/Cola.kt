package tda

// Interfaz que representa una cola de elementos
interface Cola<T> {
    fun push(item: T): Boolean // Insertar
    fun pop(): T?
    fun isEmpty(): Boolean
}