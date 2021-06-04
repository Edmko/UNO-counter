package ua.edmko.unocounter.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

typealias Score = Int
typealias PlayerId = Long

@Entity
data class Round(
    @PrimaryKey(autoGenerate = true) val roundId: Long = 0L,
    val gameRoundId: String,
    val roundNum: Int = 1,
    @TypeConverters(RoundsConverter::class)
    val result: MutableMap<PlayerId, Score> = mutableMapOf()
){
    companion object{
        fun getRoundStub() = Round(gameRoundId = UUID.randomUUID().toString())
    }
}

class RoundsConverter {
    @TypeConverter
    fun fromRounds(rounds: Map<PlayerId, Score>): String {
        return Gson().toJson(rounds)
    }

    @TypeConverter
    fun toRounds(value: String): Map<PlayerId, Score> {
        val type = object : TypeToken<Map<PlayerId, Score>>() {}.type
        return Gson().fromJson(value, type)
    }
}