package ua.edmko.data.mappers

import ua.edmko.data.local.entities.GameLocal
import ua.edmko.domain.entities.Game
import javax.inject.Inject

internal class GameMapper @Inject constructor(
    private val gameSettingsMapper: GameSettingsMapper,
    private val playerMapper: PlayerMapper,
    private val roundMapper: RoundMapper,
) : DataMapper<GameLocal, Game> {

    override fun map(domain: Game): GameLocal {
        val settings = gameSettingsMapper.map(domain.gameSettings)
        val players = domain.players.map(playerMapper::map)
        val rounds = domain.rounds.map(roundMapper::map)
        return GameLocal(settings, players, rounds)
    }

    override fun map(local: GameLocal): Game {
        val settings = gameSettingsMapper.map(local.gameSettings)
        val players = local.players.map(playerMapper::map)
        val rounds = local.rounds.map(roundMapper::map)
        return Game(settings, players, rounds)
    }
}
