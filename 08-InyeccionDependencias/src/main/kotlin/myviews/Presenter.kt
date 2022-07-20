package koin.myviews

import java.util.*

data class Presenter(private val navigator: Navigator) {
    private val id = UUID.randomUUID()
}
