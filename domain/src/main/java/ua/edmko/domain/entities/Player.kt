package ua.edmko.domain.entities

data class Player (
    val playerId: Long,
    val name: String,
    val isSelected: Boolean = true
): Entity {

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