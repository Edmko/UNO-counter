package ua.edmko.data.mappers

import ua.edmko.data.local.entities.RoundLocal
import ua.edmko.domain.entities.Round

class RoundMapper : DataMapper<RoundLocal, Round> {
    override fun map(domain: Round): RoundLocal {
        return RoundLocal(
            domain.roundId,
            domain.gameRoundId,
            domain.roundNum,
            domain.result,
            domain.winner
        )
    }

    override fun map(local: RoundLocal): Round {
        return Round(
            local.roundId,
            local.gameRoundId,
            local.roundNum,
            local.result,
            local.winner
        )
    }
}