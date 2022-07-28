package koin.personas

import koin.personas.controllers.PersonasController
import koin.personas.repositories.IPersonasRepository
import koin.personas.repositories.PersonasRepository
import koin.personas.services.IPersonasStorage
import koin.personas.services.PersonasStorageDataBase
import koin.personas.services.PersonasStorageFile
import org.koin.core.qualifier.named
import org.koin.dsl.module

// Modulo que indica cómo son las dependencias.
val PersonasModule = module {

    // Hay dos formas de repositorio de personas. y le decimos que al principio (para probar)
    single<IPersonasStorage>(named("DataBaseStorage")) { PersonasStorageDataBase() }
    single<IPersonasStorage>(named("FileStorage")) { PersonasStorageFile() }
    // O por defecto
    single<IPersonasStorage> { PersonasStorageDataBase() }

    // Creamos los posibles repositorios de personas
    single<IPersonasRepository>(named("DataBaseRepository")) { PersonasRepository(get(named("DataBaseStorage"))) }
    single<IPersonasRepository>(named("FileRepository")) { PersonasRepository(get(named("FileStorage"))) }
    // O por defecto
    factory<IPersonasRepository> { PersonasRepository(get()) }

    // Creamos el controlador
    single(named("DataBaseController")) { PersonasController(get(named("DataBaseRepository"))) }
    single(named("FileController")) { PersonasController(get(named("FileRepository"))) }
    // O por defecto
    single { PersonasController(get()) }

    // De manera básica
    /*single<IPersonasStorage> { PersonasStorageDataBase() }
    single<IPersonasRepository> { PersonasRepository(get()) }
    single { PersonasController(get()) }*/

}