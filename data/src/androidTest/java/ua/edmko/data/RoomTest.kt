package ua.edmko.data

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.room.testing.MigrationTestHelper
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ua.edmko.data.local.AppDatabase
import java.io.IOException

private const val TEST_DB = "migration-test"

@RunWith(AndroidJUnit4::class)
class RoomTest {

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        instrumentation = InstrumentationRegistry.getInstrumentation(),
        databaseClass = AppDatabase::class.java,
    )

    @Test
    @Throws(IOException::class)
    fun migrate1To2() {
        helper.createDatabase(TEST_DB, 1).apply {
            insert(
                "GameCrossRef",
                SQLiteDatabase.CONFLICT_FAIL,
                ContentValues().apply {
                    put("playerId", 1L)
                    put("gameSettingsId", "settingId")
                },
            )
            close()
        }
        helper.runMigrationsAndValidate(TEST_DB, 2, true)
    }
}
