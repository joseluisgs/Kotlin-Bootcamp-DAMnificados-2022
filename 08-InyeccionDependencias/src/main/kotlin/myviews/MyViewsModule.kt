package koin.myviews

import org.koin.dsl.module

val MyViewsModule = module {
    // Lo voy a definir todo como Singleton
    single { Navigator() }
    // Este como factory, solo para variar :)
    factory { Presenter(get()) }
}
