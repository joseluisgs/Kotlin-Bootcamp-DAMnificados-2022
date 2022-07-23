package utils

import java.io.File

/**
 * De una cadena obtiene el path de un sistema de ficheros.
 */
fun String.toSystemPath() = replace("/", File.separator).replace("\\", File.separator)