package ua.edmko.domain.entities

import java.util.UUID

typealias Score = Int
typealias PlayerId = Long

data class Round(
    val roundId: String = UUID.randomUUID().toString(),
    val gameRoundId: String,
    val roundNum: Int,
    val result: MutableMap<PlayerId, Score> = mutableMapOf(),
    val winner: PlayerId? = null,
) : Entity {
    companion object {
        val empty = Round("", "", 1)
    }
}
