package com.company.jetpackbottombarexample.main

import androidx.navigation3.runtime.NavKey
import com.company.jetpackbottombarexample.booking.BookingNavigation
import kotlinx.serialization.Serializable

@Serializable
sealed class MainScreenNavigation : NavKey {

    @Serializable
    data object TabScreen : MainScreenNavigation()

    @Serializable
    data class HomeSeparateScreen(val id : String) : MainScreenNavigation()

    @Serializable
    data class BookingFlow(val bookingNavigation: BookingNavigation) : MainScreenNavigation()
}