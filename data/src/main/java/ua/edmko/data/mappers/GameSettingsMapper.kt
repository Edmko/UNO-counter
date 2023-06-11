package ua.edmko.data.mappers

import ua.edmko.data.local.entities.GameSettingsLocal
import ua.edmko.domain.entities.GameSettings
import javax.inject.Inject

internal class GameSettingsMapper @Inject constructor() : DataMapper<GameSettingsLocal, GameSettings> {
    override fun map(domain: GameSettings): GameSettingsLocal {
        return GameSettingsLocal(domain.type, domain.goal, domain.id)
    }

    override fun map(local: GameSettingsLocal): GameSettings {
        return GameSettings(local.type, local.goal, local.gameSettingsId)
    }
}
