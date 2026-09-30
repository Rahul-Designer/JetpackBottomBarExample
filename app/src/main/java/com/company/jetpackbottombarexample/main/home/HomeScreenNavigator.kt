package com.company.jetpackbottombarexample.main.home

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class HomeScreenNavigator : NavKey{

    @Serializable
    data object HomeMainScreen : HomeScreenNavigator()

    @Serializable
    data class HomeScreenOne(val name : String) : HomeScreenNavigator()

    @Serializable
    data class HomeScreenTwo(val name: String) : HomeScreenNavigator()
}