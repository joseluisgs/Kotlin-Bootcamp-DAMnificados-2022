// Punto de entrada siempre es una función main

const val NOMBRE = "KOTLIN"

fun main(args: Array<String>) {
    println("Hola $NOMBRE")

    // Tipos de datos nullables y no nullables
    val num: Int? = null
    val num2: Int = 2
    val num3: Int = 3

    // Casting explicitos datos
    var num4: Float = num3.toFloat()

    // variables de solo lectura o lectura y escritura
    val nombre = "Nombre"
    // nombre = "Hola"
    num4 = 0.0F

    // string templates
    val casa = "Esto es una casa"
    val color = "roja"
    println("$casa $color con longitud ${casa.length}")

    val json = """{
    "arrayColores":[{
            "rojo":"$casa",
            "verde":"$color",
            "azul":"${casa.length}",
            "cyan":"#0ff",
            "magenta":"#f0f",
            "amarillo":"#ff0",
            "negro":"#000"
        }
    ]
    }""".trimIndent()
    println(json)

    // Rangos
    val existe = 3
    print("$existe: ")
    println(existe in (1..10))
    println(existe !in (1..10))

    // Arrays
    val array1 = Array<Int>(10) { 0 }
    var array2 = IntArray(10)
    println(array1[4])
    val matrix = Array(10) { IntArray(10) }
    println(matrix[1][3])

}