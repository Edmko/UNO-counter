package ua.edmko.domain.entities

data class Player(
    val playerId: Long,
    val name: String,
    val isSelected: Boolean = true,
) : Entity {

    companion object {

        val STUB = listOf(
            Player(266542, "John Smith"),
            Player(254153, "Dali Bali"),
            Player(275414, "Man Quite", isSelected = false),
            Player(654116, "Tatu dali man", isSelected = false),
            Player(254115, "Konkistadoro Atilla"),
        )
    }
}
