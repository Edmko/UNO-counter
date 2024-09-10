package ua.edmko.domain.entities

import java.util.UUID

data class GameSettings(
    val type: GameType,
    val goal: Int,
    val id: String = UUID.randomUUID().toString(),
) : Entity {

    companion object {

        val EMPTY = GameSettings(id = "", type = GameType.CLASSIC, goal = 500)
    }
}
