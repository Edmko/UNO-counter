package ua.edmko.unocounter.domain.entities

data class Player (
    val id: String,
    val name: String,
    val isSelected: Boolean = false
    ){
    companion object {
        fun getPlayersStub() = listOf(
            Player("qwret34542", "John Smith"),
            Player("qwret34541", "Dali Bali"),
            Player("qwret34547", "Man Quite"),
            Player("qwret3455", "Vasya"),
            Player("qwret345367", "John Smith"),
            Player("qwret34632", "Dali Bali"),
        )
    }
}