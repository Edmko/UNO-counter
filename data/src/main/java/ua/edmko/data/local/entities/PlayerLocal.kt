package ua.edmko.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
internal data class PlayerLocal(
    @PrimaryKey(autoGenerate = true) val playerId: Long = 0,
    val name: String,
    val isSelected: Boolean = true,
) : DaoModel() {

    companion object {
        fun getPlayersStub() =
            listOf(
                PlayerLocal(2665421421, "John Smith"),
                PlayerLocal(254153, "Dali Bali"),
                PlayerLocal(2754141, "Man Quite", isSelected = false),
                PlayerLocal(54111, "Vasya"),
                PlayerLocal(1614, "John Smith"),
                PlayerLocal(654116, "Tatu dali man", isSelected = false),
                PlayerLocal(2541154, "KonkistadoroAtilla"),
            )
    }
}
