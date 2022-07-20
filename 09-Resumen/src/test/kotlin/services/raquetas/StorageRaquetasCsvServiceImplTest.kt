package services.raquetas

import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith
import java.io.File

@ExtendWith(MockKExtension::class)
internal class StorageRaquetasCsvServiceImplTest {

    private val storage: StorageRaquetasCsvService = StorageRaquetasCsvServiceImpl()
    private val fileIn = File("src/test/resources/raquetas.csv")
    private val fileOut = File("src/test/resources/raquetas-output.csv")

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
}