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

fun String.isPalindrome() = this == this.reversed()

// Podemos pasarle un lambda a una función de extensión para programar un comportamiento personalizado.
fun String.filer(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    for (c in this) if (predicate(c)) sb.append(c)
    return sb.toString()
}

fun List<Int>.filer(predicate: (Int) -> Boolean): List<Int> {
    val r = mutableListOf<Int>()
    for (n in this) if (predicate(n)) r.add(n)
    return r
}

fun <T> List<T>.filerGeneric(predicate: (T) -> Boolean): List<T> {
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
    println("kotlin".isPalindrome())
    println("sarabaras".isPalindrome())
    println("sarabaras".filer { it.isLetter() })
    println("sar1baras".filer { it.isDigit() })
    println("sarabaras".filer { it in 'a'..'c' })
    println(listOf(1, 2, 3, 4, 5).filer { it <= 2 })
    println(listOf(1, 2, 3, 4, 5).filer { it % 2 == 0 })
    println(listOf(1, 2, 3, 4, 5).filer { it.isPrime() })
    println(listOf(1, 2, 3, 4, 5).filerGeneric { it % 2 == 0 })
    println(listOf("sarabaras", "casa").filerGeneric { it.isPalindrome() })
    listOf("sarabaras", "casa").myForEach { println(it) }
    listOf(1, 2, 3, 4, 5).myForEach { println(it) }
}