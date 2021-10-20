package ua.edmko.domain.entities

typealias Score = Int
typealias PlayerId = Long

data class Round(
    val roundId: Long,
    val gameRoundId: String,
    val roundNum: Int,
    val result: MutableMap<PlayerId, Score> = mutableMapOf(),
    val winner: PlayerId? = null
): Entity {
    companion object {
    }
}