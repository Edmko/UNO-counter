package ua.edmko.unocounter.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
data class Player (
    @PrimaryKey(autoGenerate = true) val playerId: Long = 0,
    val name: String,
    val isSelected: Boolean = true
) {

    companion object {
        fun getPlayersStub() = listOf(
            Player(2665421421, "John Smith"),
            Player(254153, "Dali Bali"),
            Player(2754141, "Man Quite", isSelected = false),
            Player(54111, "Vasya"),
            Player(1614, "John Smith"),
            Player(654116, "Tatu dali man", isSelected = false),
            Player(2541154, "KonkistadoroAtilla")
        )
    }
}