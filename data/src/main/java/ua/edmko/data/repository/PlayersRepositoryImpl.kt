package ua.edmko.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ua.edmko.data.local.PlayersDao
import ua.edmko.data.local.entities.PlayerLocal
import ua.edmko.data.mappers.PlayerMapper
import ua.edmko.domain.entities.Player
import ua.edmko.domain.repository.PlayersRepository
import javax.inject.Inject

class PlayersRepositoryImpl @Inject constructor(
    private val dao: PlayersDao,
    private val playerMapper: PlayerMapper
) : PlayersRepository {

    override suspend fun createPlayer(name: String) {
        dao.insertPlayer(PlayerLocal(name = name))
    }

    override suspend fun removePlayer(id: Long) {
        dao.deletePlayerById(id)
    }

    override fun observePlayers(): Flow<List<Player>> {
        return dao.observePlayers().map { it.map(playerMapper::map) }
    }

    override suspend fun getSelectedPlayers(): List<Player> {
        return dao.getSelectedPlayers().map(playerMapper::map)
    }

    override suspend fun updatePlayer(player: Player) {
        return dao.updatePlayer(playerMapper.map(player))
    }
}