package extension

// Las funciones de extension son funciones que se pueden usar en una clase, pero no en una instancia de la clase.
// Nos permite extender la funcionalidad de una clase o un tipo, sin necesidad de usar la herencia
// De esa manera podemos "dopar" una funcionalidad a una clase o un tipo de cosas que nos interesan y no tienen
// y usar dicha funcionalidad como si fuera un método implementado de la misma.
// El Receiver o receptor es el mismo objeto del tipo que extendemos.

// Las funciones de extensión deben empezar con el Tipo , y nombre del metodo.


fun isEvenfun(number: Int) = number % 2 == 0

fun Int.isEven() = this % 2 == 0

fun isOddfun(number: Int) = !isEvenfun(number)
fun Int.isOdd() = !this.isEven()

fun Int.isPrime() = (2 until this).none { this % it == 0 }

fun Int.isPrimeOld(): Boolean {
    if (this == 1) return false
    if (this == 2) return true
    if (this % 2 == 0) return false
    for (i in 2 until this) {
        if (this % i == 0) return false
    }
    return true
}

data class Persona(val nombre: String, val edad: Int)

fun Persona.isAdult() = this.edad >= 18
fun Persona.presentacion() = "Hola, me llamo ${this.nombre} y tengo ${this.edad} años"


fun String.isPalindrome() = this == this.reversed()

// Podemos pasarle un lambda a una función de extensión para programar un comportamiento personalizado.
fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    for (c in this) if (predicate(c)) sb.append(c)
    return sb.toString()
}

fun List<Int>.filter(predicate: (Int) -> Boolean): List<Int> {
    val r = mutableListOf<Int>()
    for (n in this) if (predicate(n)) r.add(n)
    return r
}

fun <T> List<T>.filterGeneric(predicate: (T) -> Boolean): List<T> {
    val r = mutableListOf<T>()
    for (n in this) if (predicate(n)) r.add(n)
    return r
}

inline fun <T> Collection<T>.myForEach(action: (T) -> Unit): Unit {
    for (element in this) action(element)
}

fun main() {
    println(isEvenfun(2))
    println(2.isEven())
    println(isOddfun(3))
    println(3.isOdd())
    println(2.isPrime())
    println(3.isPrime())
    println(4.isPrime())
    println(4.isPrimeOld())

    val persona = Persona("Juan", 20)
    println(persona)
    println(persona.isAdult())
    println(persona.presentacion())

    println("kotlin".isPalindrome())
    println("sarabaras".isPalindrome())

    println("sarabaras".filter { it.isLetter() })
    println("sa12345as".filter { it.isLetter() })
    println("sar1baras".filter { it.isDigit() })
    println("sarabaras".filter { it in 'a'..'c' })


    println(listOf(1, 2, 3, 4, 5).filter { it <= 2 })
    println(listOf(1, 2, 3, 4, 5).filter { it % 2 == 0 })
    println(listOf(1, 2, 3, 4, 5).filter { it.isEven() })
    println(listOf(1, 2, 3, 4, 5).filter { it.isPrime() })

    println(listOf(1, 2, 3, 4, 5).filterGeneric { it % 2 == 0 })
    println(listOf("sarabaras", "casa", "oddo").filterGeneric { it.isPalindrome() })

    listOf("sarabaras", "casa").forEach { println(it) }
    listOf("sarabaras", "casa").myForEach { println(it) }

    listOf("sarabaras", "casa").myForEach { x ->
        println("${x.uppercase()}: ${x.length}")
    }

    listOf("sarabaras", "casa").myForEach {
        println("${it.uppercase()}: ${it.length}")
    }

    listOf(1, 2, 3, 4, 5).myForEach { println(it) }

    listOf(1, 2, 3, 4, 5).myForEach { println("$it es Par?: ${it.isEven()} y es Primo?: ${it.isPrime()}") }
}