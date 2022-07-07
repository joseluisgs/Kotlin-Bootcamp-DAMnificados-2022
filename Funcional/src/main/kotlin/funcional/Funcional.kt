package funcional

// En Kotlin las funciones son ciudadanos de primera clase.
// Esto implica que las funciones pueden ser pasadas como argumentos a otras funciones.
// o almacenarse en variables para luego usarlas
// Esto es muy interesante pues nos permite definir la funcionalidad en base a una especificación
// es decir, dotar de un coportamiento cuando lo necesitemos.

// Esta es tu función de toda la vida :)
fun sum(a: Int, b: Int): Int {
    return a + b
}

// Puedes almacenarla en una variable??? Esto es el tipo función
val suma = fun(a: Int, b: Int): Int {
    return a + b
}

// Y si ahora le definimos el comportamiento que queremos?
// Esto es un tipo función, decimos que add es una variable
// de tipo función: (Int, Int) -> Int (acepta dos parametros de tipo enterio y devuleve un entero)
var suma2: (Int, Int) -> Int = suma

// Y si ahora le definimos el comportamiento que queremos? en base a su tipo función (Int, Int) -> Int
// Esto es una función anónima, o lambda, es decir, programamos el comportamiento en base a la
// Especificación del tipo de dato que queremos.
// una lambda es: { x: Int, y: Int -> x + y }
var add: (Int, Int) -> Int = { a, b -> a + b }

// También podemos psarle los tipos directamente: (Int, Int) -> Int
var subtract = { x: Int, y: Int -> x - y }

// A partir de aquí se nos abre un mundo... como pasar el comportamiento o función como paráetro a otra función
// Dado dos numeros vamos a oprrar con ellos de cualquier manera que queramos en base a una función
// De est amanera no acoplamos el comportamiento de la función con el código de la función
// Si no usamos el comportameinto que queramos en el momento que queramos
// Siempre y cuando cuumplamos con la expresión de la función (por eso son tipos)
fun operacion(a: Int, b: Int, op: (Int, Int) -> Int): Int {
    return op(a, b)
}

var display: () -> Unit = { println("Hola") }

fun displayFun() = println("Hola")

fun tiempo(accion: () -> Unit) {
    val tIni = System.nanoTime()
    accion()
    val tFin = System.nanoTime()
    println("Tiempo de ejecucion: ${(tFin - tIni) / 1000000} ms")
}

// Inline nos cokpia el codigo exacto detro de la funcion, a veces interesa eso que repetor referencias a objetos
// Esto es avanzado
inline fun miRepeat(times: Int, action: (Int) -> Unit) {
    for (index in 0 until times) {
        action(index)
    }
}

inline fun myForeach(collection: Collection<String>, action: (String) -> Unit) {
    for (item in collection) {
        action(item)
    }
}

fun main() {
    display()
    displayFun()
    println(sum(1, 2))
    println(suma(1, 2))
    println(suma2(1, 2))
    println(add(1, 2))
    println(subtract(1, 2))
    // Podemos pasarle una función como parámetro a otra función
    println(operacion(1, 2, add))
    println(operacion(1, 2, subtract))
    // O definir el comportamiento en base a una función anónima (lambda)
    println(operacion(1, 2) { a, b -> a + b })
    println(operacion(1, 2) { a, b -> a - b })
    println(operacion(1, 2) { a, b -> a * b })
    println(operacion(1, 2) { a, b -> a / b })
    println(operacion(1, 2) { a, b -> a % b })
    tiempo { println("Hola") }
    tiempo {
        add(1, 2)
    }
    tiempo {
        // puedes ver la implementación de repeat??
        repeat(10_0000) {
            operacion(1, 2) { a, b -> a * b } // Esto es una función anónima
        }
    }

    tiempo(accion = {
        for (i in 0 until 10_0000) {
            suma(1, 2)
        }
    })

    miRepeat(10_0000) {
        operacion(1, 2) { a, b -> a * b } // Esto es una función anónima
    }

    miRepeat(times = 10_0000, action = {
        operacion(1, 2) { a, b -> a * b } // Esto es una función anónima
    })

    miRepeat(times = 10_0000, action = {
        "abracadabra".uppercase()
        "abracadabra".length
        operacion(1, 2) { a, b -> a * b } // Esto es una función anónima
    })

    myForeach(listOf("a", "b", "c")) {
        println(it)
    }

    myForeach(collection = listOf("a", "b", "c"), action = {
        println(it.uppercase())
    })

    myForeach(collection = listOf("a", "b", "c"), action = { x ->
        println(x.lowercase())
    })

    myForeach(collection = listOf("ab", "bc", "cd"), action = { x ->
        println(x.drop(1))
    })

    myForeach(listOf("ab", "bc", "cd")) { println(it.drop(1)) }
}
