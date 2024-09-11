package ua.edmko.data.preferences

import android.content.Context
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import ua.edmko.domain.entities.Theme
import ua.edmko.domain.repository.Preferences
import java.io.IOException

const val DATA_STORE_NAME = "user_settings"
val Context.dataStore by dataStore(DATA_STORE_NAME, SettingsSerializer)

internal class DataStoreManager(private val context: Context) : Preferences {

    override val theme: Flow<Theme> = context.dataStore.data
        .map { it.theme.toTheme() }
        .catch { exception ->
            if (exception is IOException) {
                emit(Theme.DARK)
            } else {
                throw exception
            }
        }

    override suspend fun setTheme(theme: Theme) {
        context.dataStore.updateData { prefs ->
            prefs.toBuilder().setTheme(theme.toProto()).build()
        }
    }
}
