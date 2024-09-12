package ua.edmko.navigation.commands

import androidx.navigation.NavOptionsBuilder
import ua.edmko.core.base.NavigationCommand
import ua.edmko.privacy.PolicyRoute

internal val PolicyCommand = object : NavigationCommand<PolicyRoute> {
    override val route: PolicyRoute = PolicyRoute
    override val builder: NavOptionsBuilder.() -> Unit = {}
}
