package com.company.jetpackbottombarexample.auth.navigation

import com.company.jetpackbottombarexample.auth.authScreens.AuthScreenManager

sealed class NavigationCommand {
    data class Navigate(val screen: AuthScreenManager) : NavigationCommand()
    data class NavigateWithClear(val screen: AuthScreenManager) : NavigationCommand()
    object NavigateBack : NavigationCommand()
    object ClearBackstack : NavigationCommand()
}