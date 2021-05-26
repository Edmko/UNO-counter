package ua.edmko.unocounter.domain.repository

import kotlinx.coroutines.flow.Flow
import ua.edmko.unocounter.domain.entities.Player

interface PlayersRepository {
    suspend fun createPlayer(player: Player)
    suspend fun removePlayer(id: Long)
    fun observePlayers(): Flow<List<Player>>
    suspend fun getSelectedPlayers(): List<Player>
    suspend fun updatePlayer(player: Player)
}