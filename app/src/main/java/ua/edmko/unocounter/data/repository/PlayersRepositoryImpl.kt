package ua.edmko.unocounter.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ua.edmko.unocounter.data.local.PlayerDatabase
import ua.edmko.unocounter.data.local.PlayersDao
import ua.edmko.unocounter.data.local.asDomain
import ua.edmko.unocounter.domain.entities.Player
import ua.edmko.unocounter.domain.repository.PlayersRepository
import javax.inject.Inject

class PlayersRepositoryImpl @Inject constructor(private val dao: PlayersDao): PlayersRepository {
    override suspend fun createPlayer(player: Player) {
        dao.insertPlayer(PlayerDatabase(player.id, player.name, player.isSelected))
    }

    override suspend fun removePlayer(id: String) {
        dao.deletePlayerById(id)
    }

    override fun observePlayers(): Flow<List<Player>> {
        return dao.getAllPlayers().map { it.map { it.asDomain() }}
    }

    override suspend fun getSelectedPlayers(): List<Player> {
        return dao.getSelectedPlayers().map { it.asDomain() }
    }

    override suspend fun updatePlayer(player: Player) {
        return dao.updatePlayer(PlayerDatabase(player.id, player.name, player.isSelected))
    }
}