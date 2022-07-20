package koin.cafeteras

import org.koin.dsl.module

val CafeterasModule = module {
    // Lo voy a definir todo como Singleton
    single<Calentador> { CalentadorElectrico() }
    single<Bomba> { Termosifon(get()) }
    single { Cafetera(get(), get()) }
}
