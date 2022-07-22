import entities.RaquetaDao
import entities.TenistaDao
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.koin.core.qualifier.named
import org.koin.dsl.module
import repositories.raquetas.RaquetasRepository
import repositories.raquetas.RaquetasRepositoryImpl
import repositories.tenistas.TenistasRepository
import repositories.tenistas.TenistasRepositoryImpl

// OJO, no voy a usar anotaciones porque falla con los object de DAO al no conocer el tipo de la entidad
// Prero no pasa nada porque al ser tan sencillo, con cuatro líneas aquí lo resuelvo
val AppModuleDI = module {
    // Lo voy a definir todo como Singleton

    // DAOs
    single<UUIDEntityClass<RaquetaDao>>(named("RaquetaDao")) { RaquetaDao }
    single<UUIDEntityClass<TenistaDao>>(named("TenistaDao")) { TenistaDao }

    // Repositories
    single<RaquetasRepository> { RaquetasRepositoryImpl(get(named("RaquetaDao"))) }
    single<TenistasRepository> { TenistasRepositoryImpl(get(named("TenistaDao")), get(named("RaquetaDao"))) }
}
