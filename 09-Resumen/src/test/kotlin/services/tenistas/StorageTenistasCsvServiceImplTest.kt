package services.tenistas

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import utils.toSystemPath
import java.io.File

internal class StorageTenistasCsvServiceImplTest {

    private val storage: StorageTenistasCsvService = StorageTenistasCsvServiceImpl()
    private val fileIn = File("src/test/resources/tenistas.csv".toSystemPath())
    private val fileOut = File("src/test/resources/tenistas-output.csv".toSystemPath())

    @Test
    fun loadFromFile() {
        val res = storage.loadFromFile(fileIn).sortedBy { it.ranking }
        val tenista = res[0]
        assertAll(
            { assert(res.isNotEmpty()) },
            { assert(tenista.ranking == 1) }
        )
    }

    @Test
    fun saveToFile() {
        val res = storage.loadFromFile(fileIn)
        storage.saveToFile(fileOut, res)
        val res2 = storage.loadFromFile(fileOut)
        assertAll(
            { assert(res.isNotEmpty()) },
            { assert(res2.isNotEmpty()) },
            { assert(res.size == res2.size) },
            { assert(res == res2) }
        )
    }

    @Test
    fun loadFromFileException() {
        val ex = assertThrows<IllegalArgumentException> {
            storage.loadFromFile(File("error.csv"))
        }

        assert(ex.message == "El fichero error.csv no existe")
    }
}