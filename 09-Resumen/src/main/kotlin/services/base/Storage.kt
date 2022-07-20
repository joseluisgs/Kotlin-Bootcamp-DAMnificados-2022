package services.base

/**
 * Sistema de almacenamiento básico
 */
interface Storage<T> {
    /**
     * Carga de un fichero de almacenamiento
     * @param fileName Fichero de almacenamiento
     * @returns Objeto de almacenamiento
     */
    fun loadFromFile(fileName: String): List<T>

    /**
     * Guardado de un fichero de almacenamiento
     * @param fileName Fichero de almacenamiento
     * @param data Objeto de almacenamiento
     */
    fun saveToFile(fileName: String, data: List<T>)
}