import java.util.*

// para iniciar programa en kotlin necesitamos una fun main()
fun main() {


    //mensaje de inicio
    println("Bienbenido a el juego de la mosca en Kotlin lineal")

    //inicio
    val size = getSize()

    //hacer tablero
    var matriz = Array<Int>(size){0}

    //juego mosca
    initJuego(matriz)


    println("El juego ha finalizado, has conseguido matar a la mosca")

}

/**
 * dinámica del juego
 */
fun initJuego(m: Array<Int>) {
    var matriz = m;

    var isDead : Boolean

    var isNear : Boolean

    matriz = movePosition(matriz)

    do {

        var golpe :Int = getGolpe(matriz)

        isDead= lookIsDead(matriz , golpe)

        if(!isDead){
            println("no la has dado, mala suerte")
            isNear = lookIsNear(matriz, golpe)
            if(isNear){
                println("Pero has estado muy cerca, la mosca se ha dado cuenta de que quieres cazarla " +
                        "\n y se ha movido de sitio")
                movePosition(matriz)
            }
        }

    }while (!isDead)

    printTable(matriz)


}

/**
 * funcion que pide al jugador un numero entre 0 y el numero maximo de
 * casilas del tablero
 * @return numero int
 */
fun getGolpe(matriz: Array<Int>): Int {
    var eleccion : Int?
    do {

        println("dime el numero entre 0 y ${matriz.size-1} para dar el golpe en la mesa y cazar la mosca")
        eleccion  = readLine()?.toIntOrNull()

        // para que esten incluidos el 4 y el 10
    }while ((eleccion == null) || (eleccion !in (0..matriz.size-1)))

    return eleccion
}

/**
 * funcion reinicia el tablero moviendo la mosca a una posicion nueva
 * @return tablero nuevo
 */
fun movePosition(matriz: Array<Int>): Array<Int> {
    val random = Random()
    var aleatorio : Int
    do {
        aleatorio = random.nextInt(10)
    }while (aleatorio in 4..10)

    val newArray = Array<Int>(matriz.size){0}
    newArray[aleatorio]=8

    return newArray
}

/**
 * funcion que comprueva si la posicion elegida para el golpe
 * es la siguiente o anterior a la casilla de la mosca
 * @return true s es asi
 */
fun lookIsNear(matriz: Array<Int>, golpe: Int): Boolean {
    if(golpe>0){
        if(matriz[golpe-1]==8){
            return true
        }
    }
    if (golpe<(matriz.size-1)){
        if(matriz[golpe+1]==8){
            return true
        }
    }
    return false
}

/**
 * funcion que comprueva si la posicion elegida para el golpe
 * es la de la mosca
 * @return true s es asi
 */
fun lookIsDead(matriz: Array<Int>, golpe: Int): Boolean {
    return matriz[golpe]==8
}

/**
 * funcion que imprime el tablero de forma legible
 */
fun printTable(matriz: Array<Int>) {
    println("Aquí tenías el tablero secreto")
    print("[")
    for (i in matriz){
        print(" $i ")
    }
    println("]")
}

/**
 * funcion que pide al jugador un numero entre 4 y 10
 * para iniciar las casillas del tablero
 * @return numero int
 */
fun  getSize(): Int {
    var eleccion : Int?
    do {


        println("dime el numero entre 4 y 10 para iniciar tu tablero de juego")
        eleccion  = readLine()?.toIntOrNull()

        // para que esten incluidos el 4 y el 10
    }while ((eleccion == null) || (eleccion !in (4..10)))

    println("Muy bien, has elegido el $eleccion comencemos!")

    return eleccion
}