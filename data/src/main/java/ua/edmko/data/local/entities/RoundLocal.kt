package ua.edmko.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

typealias Score = Int
typealias PlayerId = Long

@Entity
internal data class RoundLocal(
    @PrimaryKey val roundId: String,
    val gameRoundId: String,
    val roundNum: Int = 1,
    val result: MutableMap<PlayerId, Score> = mutableMapOf(),
    val winner: PlayerId? = null,
) : DaoModel()

internal class RoundsConverter {

    @TypeConverter
    fun fromRounds(rounds: MutableMap<PlayerId, Score>): String {
        return Gson().toJson(rounds)
    }

    @TypeConverter
    fun toRounds(value: String): MutableMap<PlayerId, Score> {
        val type = object : TypeToken<Map<PlayerId, Score>>() {}.type
        return Gson().fromJson(value, type)
    }
}
