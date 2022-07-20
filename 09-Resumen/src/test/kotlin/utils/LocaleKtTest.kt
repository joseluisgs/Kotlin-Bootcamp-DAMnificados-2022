package utils

import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class LocaleKtTest {

    @Test
    fun toLocalDate() {
        val date = LocalDate.parse("1987-05-22")
        val salida = "22 may 1987"
        assert(date.toLocalDate() == salida)
    }

    @Test
    fun toLocalMoney() {
        val money = 3.45
        val salida = "3,45 €"
        assert(money.toLocalMoney() == salida)
    }

    @Test
    fun toLocalNumber() {
        val number = 344.99
        val salida = "344,99"
        assert(number.toLocalNumber() == salida)
    }
}