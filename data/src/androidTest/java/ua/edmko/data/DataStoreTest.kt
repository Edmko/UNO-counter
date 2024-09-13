package ua.edmko.data

import android.content.Context
import androidx.datastore.dataStoreFile
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test
import ua.edmko.data.preferences.DATA_STORE_NAME
import ua.edmko.data.preferences.DataStoreManager
import ua.edmko.domain.entities.Theme

internal class DataStoreTest {

    private val testContext: Context = ApplicationProvider.getApplicationContext()
    private val repository = DataStoreManager(testContext)

    @After
    fun cleanup() {
        testContext.dataStoreFile(DATA_STORE_NAME).deleteRecursively()
    }

    @Test
    fun checkDataStoreSerialization() = runTest {
        val theme = Theme.LIGHT
        repository.setTheme(theme)
        val dataStoreObject = repository.theme.first()
        assert(theme == dataStoreObject)
    }
}
