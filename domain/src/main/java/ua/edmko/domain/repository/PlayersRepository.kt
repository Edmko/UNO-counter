package ua.edmko.domain.repository

import kotlinx.coroutines.flow.Flow
import ua.edmko.domain.entities.Player

interface PlayersRepository {
    suspend fun createPlayer(name: String)
    suspend fun removePlayer(id: Long)
    fun observePlayers(): Flow<List<Player>>
    suspend fun getSelectedPlayers(): List<Player>
    suspend fun updatePlayer(player: Player)
}
