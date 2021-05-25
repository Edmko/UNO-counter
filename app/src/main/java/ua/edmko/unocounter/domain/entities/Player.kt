package ua.edmko.unocounter.domain.entities

data class Player(
    val id: String,
    var name: String,
    var isSelected: Boolean = false
) {

    companion object {
        fun getPlayersStub() = listOf(
            Player("qwret34542", "John Smith"),
            Player("qwret34541", "Dali Bali"),
            Player("qwret34547", "Man Quite"),
            Player("qwret3455", "Vasya"),
            Player("qwret345367", "John Smith"),
            Player("qwret34632", "Dali Bali"),
            Player("qwret3463112", "Dali Bali"),
            Player("qwret3463526", "Dali Bali"),
            Player("qwret3463252", "Dali Bali"),
            Player("qwret34634", "Dali Bali"), Player("qwret34632", "Dali Bali"),
            Player("qwret34532632", "Dali Bali"),
            Player("qwret3425632", "Dali Bali"),
            Player("qwret3534632", "Dali Bali"),
            Player("qwret3462532", "Dali Bali"),

            )
    }
}