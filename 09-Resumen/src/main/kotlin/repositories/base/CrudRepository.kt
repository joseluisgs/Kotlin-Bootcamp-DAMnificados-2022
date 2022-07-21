package repositories.base

// Vamos a simular
// https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html

/**
 * Repositorio de base de datos CRUD
 */
interface CrudRepository<T, ID> {
    fun findAll(): List<T> // List<T> es una lista de T
    fun findById(id: ID): T? // nullable puede no existir
    fun save(entity: T): T // Inserta si no existe, actualiza si existe
    fun delete(entity: T): Boolean // No es obligatorio el boolean
}