package ua.edmko.unocounter.data.repository

import kotlinx.coroutines.flow.Flow
import ua.edmko.unocounter.data.local.PlayersDao
import ua.edmko.unocounter.domain.entities.Player
import ua.edmko.unocounter.domain.repository.PlayersRepository
import javax.inject.Inject

class PlayersRepositoryImpl @Inject constructor(private val dao: PlayersDao): PlayersRepository {
    override suspend fun createPlayer(player: Player) {
        dao.insertPlayer(Player(name = player.name, isSelected = player.isSelected))
    }

    override suspend fun removePlayer(id: Long) {
        dao.deletePlayerById(id)
    }

    override fun observePlayers(): Flow<List<Player>> {
        return dao.getAllPlayers()
    }

    override suspend fun getSelectedPlayers(): List<Player> {
        return dao.getSelectedPlayers()
    }

    override suspend fun updatePlayer(player: Player) {
        return dao.updatePlayer(player)
    }
}