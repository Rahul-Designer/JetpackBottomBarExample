package com.company.jetpackbottombarexample.auth.authScreens

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class AuthScreenManager : NavKey {

    @Serializable
    data object SplashScreen : AuthScreenManager()

    @Serializable
    data object SignInScreen : AuthScreenManager()

    @Serializable
    data object SignUpScreen : AuthScreenManager()

    @Serializable
    data object MainScreen : AuthScreenManager()
}