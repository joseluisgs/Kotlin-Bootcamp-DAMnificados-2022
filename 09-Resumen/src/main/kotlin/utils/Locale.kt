package utils

import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

const val language = "es"
const val country = "ES"

/**
 * Función de Extensión para convertir una fecha a un formato fecha local
 */
fun LocalDate.toLocalDate(): String {
    return this.format(
        DateTimeFormatter
            .ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale(language, country))
    )
}

/**
 * Función de Extensión para convertir un Double a un formato moneda local
 */
fun Double.toLocalMoney(): String {
    return NumberFormat.getCurrencyInstance(Locale(language, country)).format(this)
}

/**
 * Función de Extensión para convertir un Double a un formato número local
 */
fun Double.toLocalNumber(): String {
    return NumberFormat.getNumberInstance(Locale(language, country)).format(this)
}