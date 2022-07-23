package services.raquetas

import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import utils.toSystemPath
import java.io.File

@ExtendWith(MockKExtension::class)
internal class StorageRaquetasCsvServiceImplTest {

    private val storage: StorageRaquetasCsvService = StorageRaquetasCsvServiceImpl()
    private val fileIn = File("src/test/resources/raquetas.csv".toSystemPath())
    private val fileOut = File("src/test/resources/raquetas-output.csv".toSystemPath())

    @Test
    fun loadFromFile() {
        val res = storage.loadFromFile(fileIn).sortedBy { it.marca }
        val raqueta = res[0]
        assertAll(
            { assert(res.isNotEmpty()) },
            { assert(raqueta.marca == "Babolat") }
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
            { assert(res2.size == res.size) },
            { assert(res2 == res) }
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