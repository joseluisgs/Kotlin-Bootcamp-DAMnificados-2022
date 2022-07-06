// Pares y Triples sirven para pasar un valor compuesto de dos o tres cosas
fun main() {
    val pair = Pair(1, "Juan")
    val triple = Triple(1, "Juan", "Perez")
    println(pair)
    println(pair.first)
    println(pair.second)
    println(triple)
    println(triple.first)
    println(triple.second)
    println(triple.third)
}