package com.edmko.setup

import ua.edmko.core.base.BaseNavigator

interface SetupNavigator : BaseNavigator {

    fun toPlayers()

    fun toGame(gameId: String)

    fun toSettings()
}
