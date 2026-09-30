package com.company.jetpackbottombarexample.booking

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class BookingNavigation : NavKey {

    @Serializable
    data object BookingScreen : BookingNavigation()

    @Serializable
    data class BookingDetailScreen(val id: String) : BookingNavigation()

}