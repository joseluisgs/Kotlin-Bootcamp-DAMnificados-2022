package utils

import org.junit.jupiter.api.Assertions.assertEquals


internal class PathKtTest {

    fun toSystemPath() {
        val os = System.getProperty("os.name", "unknown").lowercase()
        val pathWin = "C:\\Users\\user\\Desktop\\test.txt"
        val pathUnix = "/Users/user/Desktop/test.txt"

        if (os.contains("win")) {
            assertEquals(pathWin, pathWin.toSystemPath())
        } else {
            assertEquals(pathUnix, pathUnix.toSystemPath())
        }

    }
}