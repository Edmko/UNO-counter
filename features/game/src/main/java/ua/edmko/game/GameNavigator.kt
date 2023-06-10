package ua.edmko.game

import ua.edmko.core.base.BaseNavigator

interface GameNavigator : BaseNavigator {
    fun toEnd(winner: String)
}
