package ua.edmko.navigation.commands

import androidx.navigation.NavOptionsBuilder
import com.edmko.setup.SetupRoute
import ua.edmko.core.base.NavigationCommand

internal val GameSettingCommand = object : NavigationCommand<SetupRoute> {

    override val route: SetupRoute = SetupRoute
    override val builder: NavOptionsBuilder.() -> Unit = {}
}
