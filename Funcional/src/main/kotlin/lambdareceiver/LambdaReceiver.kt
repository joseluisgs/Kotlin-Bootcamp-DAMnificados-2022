package lambdareceiver

// Labda con Reicever es una mezcla de Lambda Funciones de extensión y Lambda Funciones de Receptor
//  Nos sirve para extender funciones de una clase y aplicar una funcion a cada una de las funciones de la clase
// con ello podemos hacer safebuilders o DSL

// Aqui esta claro que extendemos  con una función de extensión y con una función de receptor
val sum: Int.(Int) -> Int = { a -> this + a }

// Aqui extendemos con opp que a su vez como parametro usa en vez de una función o lambda una función de extensión
fun Int.opp(f: Int.() -> Int): Int = f()

// Safe Builder String con lambdas con receptor
fun myString(init: StringBuilder.() -> Unit): String {
    return StringBuilder().apply(init).toString()
}

fun main() {
    println(sum(1, 2))
    println(1.sum(2))
    println(10.opp { this.times(2) })
    println(10.opp { plus(10) })
    println(10.opp { this * 2 })
    val str = myString {
        append("Hello, ".uppercase())
        append("World!")
    }
    println(str)
}