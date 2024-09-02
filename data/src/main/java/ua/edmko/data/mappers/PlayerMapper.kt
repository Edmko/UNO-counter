package ua.edmko.data.mappers

import ua.edmko.data.local.entities.PlayerLocal
import ua.edmko.domain.entities.Player
import javax.inject.Inject

internal class PlayerMapper @Inject constructor() : DataMapper<PlayerLocal, Player> {

    override fun map(domain: Player): PlayerLocal {
        return PlayerLocal(domain.playerId, domain.name, domain.isSelected)
    }

    override fun map(local: PlayerLocal): Player {
        return Player(local.playerId, local.name, local.isSelected)
    }
}
