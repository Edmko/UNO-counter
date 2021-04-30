package ua.edmko.unocounter.domain.repository

import ua.edmko.unocounter.domain.entities.Player

interface PlayersRepository {
    suspend fun createPlayer(player: Player)
    suspend fun removePlayer(id: String)
    suspend fun getPlayers(): List<Player>
    suspend fun getSelectedPlayers(): List<Player>
}