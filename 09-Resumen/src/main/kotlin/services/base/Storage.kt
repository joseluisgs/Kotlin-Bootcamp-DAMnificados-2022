package services.base

interface Storage<T> {
    fun loadFromFile(fileName: String): List<T>
    fun saveToFile(fileName: String, data: List<T>)
}