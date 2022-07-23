import controllers.RaquetasController
import controllers.TenistasController
import entities.RaquetaDao
import entities.TenistaDao
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.koin.core.qualifier.named
import org.koin.dsl.module
import repositories.raquetas.RaquetasRepository
import repositories.raquetas.RaquetasRepositoryImpl
import repositories.tenistas.TenistasRepository
import repositories.tenistas.TenistasRepositoryImpl
import services.raquetas.StorageRaquetasCsvService
import services.raquetas.StorageRaquetasCsvServiceImpl
import services.tenistas.StorageTenistasCsvService
import services.tenistas.StorageTenistasCsvServiceImpl
import services.tenistas.StorageTenistasJsonService
import services.tenistas.StorageTenistasJsonServiceImpl

// OJO, no voy a usar anotaciones porque falla con los object de DAO al no conocer el tipo de la entidad
// Prero no pasa nada porque al ser tan sencillo, con cuatro líneas aquí lo resuelvo
// Es importante el uso de nombres, al ser genericos UUIDEntityClass, debe anotar cuales son los tipos de las entidades
// Para pasarlos al repositorio en el orden correcto cuando lo resuleva
val ResumenAppModuleDI = module {
    // Lo voy a definir todo como Singleton

    // DAOs
    single<UUIDEntityClass<RaquetaDao>>(named("RaquetaDao")) { RaquetaDao }
    single<UUIDEntityClass<TenistaDao>>(named("TenistaDao")) { TenistaDao }

    // Repositories
    single<RaquetasRepository> { RaquetasRepositoryImpl(get(named("RaquetaDao"))) }
    single<TenistasRepository> { TenistasRepositoryImpl(get(named("TenistaDao")), get(named("RaquetaDao"))) }

    // Services
    single<StorageRaquetasCsvService> { StorageRaquetasCsvServiceImpl() }
    single<StorageTenistasCsvService> { StorageTenistasCsvServiceImpl() }
    single<StorageTenistasJsonService> { StorageTenistasJsonServiceImpl() }

    // Controllers
    single { RaquetasController(get(), get()) }
    single { TenistasController(get(), get(), get()) }
}
