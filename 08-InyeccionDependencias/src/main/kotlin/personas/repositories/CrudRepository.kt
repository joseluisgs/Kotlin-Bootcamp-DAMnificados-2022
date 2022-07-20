package koin.personas.repositories

interface CrudRepository<T, ID> {
    fun save(entity: T): T // ... más métodos, si hago T...
}
